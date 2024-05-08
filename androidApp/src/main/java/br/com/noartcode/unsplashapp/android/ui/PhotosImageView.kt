package br.com.noartcode.unsplashapp.android.ui

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import br.com.noartcode.unsplashapp.android.domain.model.Photo
import br.com.noartcode.unsplashapp.android.presentation.random.imageAspectRatio
import coil.compose.AsyncImage

@Composable
fun PhotosImageView(
    photo: Photo,
    aspectRatio:Float = imageAspectRatio,
    contentScale: ContentScale = ContentScale.Crop,
    modifier: Modifier = Modifier
) {
    AsyncImage(
        model = photo.url,
        contentDescription = null,
        contentScale = contentScale,
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(aspectRatio),
    )
}