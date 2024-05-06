package br.com.noartcode.unsplashapp.android.presentation.search

import androidx.activity.compose.setContent
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
import br.com.noartcode.unsplashapp.android.MyApplicationTheme

@Composable
fun SearchPhotosScreen(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Search Photos Screen",
            fontSize = 22.sp
        )
    }

}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SearchPhotosScreen_Preview(){
    MyApplicationTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            SearchPhotosScreen()
        }
    }
}