package br.com.noartcode.unsplashapp.android.navigation.navgraphs

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.noartcode.unsplashapp.android.navigation.Screen
import br.com.noartcode.unsplashapp.android.presentation.collection.LikedPhotosScreen

@Composable
fun LikedPhotosNavGraph(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = Screen.LikedPhotosList::class,
        route = Screen.LikedPhotosRoot::class,
    ) {

        composable<Screen.LikedPhotosList>{
            LikedPhotosScreen()
        }

        composable<Screen.LikedPhotosDetail>{

        }
    }
}