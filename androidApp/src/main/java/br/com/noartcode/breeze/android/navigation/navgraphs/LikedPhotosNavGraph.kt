package br.com.noartcode.breeze.android.navigation.navgraphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.noartcode.breeze.android.navigation.Screen
import br.com.noartcode.breeze.android.presentation.collection.LikedPhotosScreen

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