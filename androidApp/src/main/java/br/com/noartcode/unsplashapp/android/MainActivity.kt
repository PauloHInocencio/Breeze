package br.com.noartcode.unsplashapp.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import br.com.noartcode.unsplashapp.android.presentation.PhotosRandomListScreen
import br.com.noartcode.unsplashapp.android.presentation.PhotosRandomListViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val viewModel:PhotosRandomListViewModel by viewModels()
                    PhotosRandomListScreen(
                        state = viewModel.state.collectAsState().value,
                        onEvent = viewModel::onEvent
                    )
                }
            }
        }
    }
}