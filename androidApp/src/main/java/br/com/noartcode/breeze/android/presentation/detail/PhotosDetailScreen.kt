package br.com.noartcode.breeze.android.presentation.detail

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.IntSize
import br.com.noartcode.breeze.android.ui.PhotosImageView

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.PhotosDetailScreen(
    state: PhotosDetailUiState,
    animatedVisibilityScope:AnimatedVisibilityScope,
    modifier:Modifier = Modifier,
) {
    var scale by remember {
        mutableFloatStateOf(1f)
    }

    var offset by remember {
        mutableStateOf(Offset.Zero)
    }

    var zoomed by remember {
        mutableStateOf(false)
    }

    BoxWithConstraints(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(9f / 16f),
        contentAlignment = Alignment.Center
    ) {

        val transformableState = rememberTransformableState { zoomChange, panChange, rotationChange ->

            scale = (scale * zoomChange).coerceIn(1f, 5f)

            val extraWidth = (scale - 1) * constraints.maxWidth
            val extraHeight = (scale -1) * constraints.maxHeight

            val maxX = extraWidth / 2f
            val maxY = extraHeight / 2f

            offset = Offset(
                x = (offset.x + scale * panChange.x).coerceIn(-maxX, maxX),
                y = (offset.y + scale * panChange.y).coerceIn(-maxY, maxY)
            )
        }
        if(state.isLoading) {
            CircularProgressIndicator()
        } else {
            state.photo?.let { photo ->
                PhotosImageView(
                    photo = photo,
                    contentScale = ContentScale.Fit,
                    aspectRatio = 9f/16f,
                    modifier = Modifier
                        .graphicsLayer {
                            scaleX = scale
                            scaleY = scale
                            translationX = offset.x
                            translationY = offset.y
                        }
                        .transformable(transformableState)
                        .pointerInput(Unit) {
                            detectTapGestures(
                                onDoubleTap = { tapOffset ->
                                    offset = if (zoomed) Offset.Zero else
                                        calculateOffset(tapOffset, size)
                                    scale = if (zoomed) 1f else 2f
                                    zoomed = !zoomed
                                }
                            )
                        }
                        .sharedElement(
                            state = rememberSharedContentState(key = "image/${photo.id}" ),
                            animatedVisibilityScope = animatedVisibilityScope,
                            boundsTransform = { _, _ ->
                                tween(durationMillis = 1000)
                            }
                        )
                )
            }
        }
    }
}


private fun calculateOffset(tapOffset: Offset, size: IntSize): Offset {
    val offsetX = (-(tapOffset.x - (size.width / 2f)) * 2f).coerceIn(-size.width / 2f, size.width / 2f)
    return Offset(offsetX, 0f)
}
