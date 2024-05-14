package br.com.noartcode.unsplashapp.android.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import br.com.noartcode.unsplashapp.android.presentation.collection.LikedPhotosScreen

@Composable
fun LikedNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Linked.List::class,
        route = Screen.Linked::class,
        modifier = modifier
    ) {

        composable<Screen.Random.List>{
            LikedPhotosScreen()
        }

        composable<Screen.Random.Detail>{

        }
    }
}