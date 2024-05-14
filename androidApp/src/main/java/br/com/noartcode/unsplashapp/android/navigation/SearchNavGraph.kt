package br.com.noartcode.unsplashapp.android.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import br.com.noartcode.unsplashapp.android.presentation.search.SearchPhotosScreen


@Composable
fun SearchNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Search.List::class,
        route = Screen.Search::class,
        modifier = modifier
    ) {

        composable<Screen.Random.List>{
            SearchPhotosScreen()
        }

        composable<Screen.Random.Detail>{

        }
    }
}