package br.com.noartcode.breeze.android.presentation.collection

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import br.com.noartcode.breeze.android.MyApplicationTheme

@Composable
fun LikedPhotosScreen(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Liked Photos Screen",
            fontSize = 22.sp
        )
    }
}


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun LikedPhotosScreen_Preview() {
    MyApplicationTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            LikedPhotosScreen()
        }
    }
}