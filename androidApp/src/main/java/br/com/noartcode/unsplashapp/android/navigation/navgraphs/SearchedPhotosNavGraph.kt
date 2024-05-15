package br.com.noartcode.unsplashapp.android.navigation.navgraphs

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.noartcode.unsplashapp.android.navigation.Screen
import br.com.noartcode.unsplashapp.android.presentation.search.SearchPhotosScreen


@Composable
fun SearchedPhotosNavGraph(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = Screen.SearchedPhotosList::class,
        route = Screen.SearchedPhotosRoot::class,
    ) {

        composable<Screen.SearchedPhotosList>{
            SearchPhotosScreen()
        }

        composable<Screen.SearchedPhotosDetail>{

        }
    }
}