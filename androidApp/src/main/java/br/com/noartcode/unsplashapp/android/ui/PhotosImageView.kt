package br.com.noartcode.unsplashapp.android.ui

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import br.com.noartcode.unsplashapp.android.presentation.imageAspectRatio
import coil.compose.AsyncImage

@Composable
fun PhotosImageView(photoUrl:String) {
    AsyncImage(
        model = photoUrl,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(imageAspectRatio),
    )
}