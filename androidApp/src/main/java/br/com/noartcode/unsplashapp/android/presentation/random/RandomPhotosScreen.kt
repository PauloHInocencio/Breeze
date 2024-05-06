package br.com.noartcode.unsplashapp.android.presentation.random

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.noartcode.unsplashapp.android.ui.PhotosImageView
import br.com.noartcode.unsplashapp.android.ui.extensions.reachedBottom

const val imageAspectRatio = 4f/5f

@Composable
fun RandomPhotosScreen(
    state: RandomPhotosUiState,
    onEvent:(RandomPhotosEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    val gridState = rememberLazyGridState()
    val reachedBottom: Boolean by remember {
        derivedStateOf { gridState.reachedBottom()  }
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(2.dp),
        verticalArrangement = Arrangement.spacedBy(2.dp),
        state = gridState,
        modifier = modifier
            .fillMaxWidth()
            .padding(2.dp)
    ) {
        items(state.photos, key = { it.id }) { photo ->
            PhotosImageView(photo)
        }
    }

    if (state.isLoading) {
        Box (modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center){
            CircularProgressIndicator()
        }
    }

    LaunchedEffect(reachedBottom) {
        if(reachedBottom) onEvent(RandomPhotosEvent.OnLoadMore)
    }
}


