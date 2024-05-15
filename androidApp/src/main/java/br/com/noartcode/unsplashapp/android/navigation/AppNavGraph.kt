package br.com.noartcode.unsplashapp.android.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.toRoute
import br.com.noartcode.unsplashapp.android.navigation.navgraphs.LikedPhotosNavGraph
import br.com.noartcode.unsplashapp.android.navigation.navgraphs.RandomPhotosNavGraph
import br.com.noartcode.unsplashapp.android.navigation.navgraphs.SearchedPhotosNavGraph
import kotlin.reflect.KClass

@Composable
fun AppNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen:Screen= navBackStackEntry?.toRoute() ?: Screen.RandomPhotosRoot

    Scaffold(
        bottomBar = {
            if (currentScreen !is Screen.RandomPhotosDetail) {
                NavigationBar {
                    listOfNavItems.forEach { navItem ->
                        NavigationBarItem(
                            selected = false,
                            onClick = {},
                            icon = { Icon(imageVector = navItem.icon, contentDescription = null) },
                        )
                    }
                }
            }

        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screen.RandomPhotosRoot::class,
            modifier = modifier.padding(paddingValues)
        ) {

            composable<Screen.RandomPhotosRoot> {
                RandomPhotosNavGraph()
            }

            composable<Screen.SearchedPhotosRoot> {
                SearchedPhotosNavGraph()
            }

            composable<Screen.LikedPhotosRoot> {
               LikedPhotosNavGraph()
            }

        }
    }
}



