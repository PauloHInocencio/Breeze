package br.com.noartcode.breeze.android.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import br.com.noartcode.breeze.android.navigation.navgraphs.LikedPhotosNavGraph
import br.com.noartcode.breeze.android.navigation.navgraphs.RandomPhotosNavGraph
import br.com.noartcode.breeze.android.navigation.navgraphs.SearchedPhotosNavGraph

@Composable
fun AppNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {

    // app NavStack
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentAppGraphScreen:Screen? = navBackStackEntry?.fromRoute()

    // random photos NavStack
    val randomPhotosNavController = rememberNavController()
    val randomPhotosNavBackStackEntry by randomPhotosNavController.currentBackStackEntryAsState()
    val currentRandomGraphScreen:Screen? = randomPhotosNavBackStackEntry?.fromRoute()



    Scaffold(
        bottomBar = {
            if (currentRandomGraphScreen !is Screen.RandomPhotosDetail) {
                NavigationBar {
                    listOfNavItems.forEach { navItem ->
                        NavigationBarItem(
                            selected = currentAppGraphScreen == navItem.route,
                            onClick = {
                                navController.navigate(navItem.route) {
                                    popUpTo(navController.graph.findStartDestination().id)
                                    launchSingleTop = true
                                }
                            },
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
                RandomPhotosNavGraph(navController = randomPhotosNavController)
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

fun NavBackStackEntry?.fromRoute() : Screen? {
    this?.destination
        ?.route
        ?.substringBefore("?")
        ?.substringBefore("/")
        ?.substringAfterLast(".")?.let { name ->
            when(name) {
                Screen.RandomPhotosDetail::class.simpleName -> return Screen.RandomPhotosDetail(id = -1)
                Screen.RandomPhotosList::class.simpleName -> return Screen.RandomPhotosList
                Screen.RandomPhotosRoot::class.simpleName -> return Screen.RandomPhotosRoot

                Screen.SearchedPhotosDetail::class.simpleName -> return Screen.SearchedPhotosDetail(id = -1)
                Screen.SearchedPhotosList::class.simpleName -> return Screen.SearchedPhotosList
                Screen.SearchedPhotosRoot::class.simpleName -> return Screen.SearchedPhotosRoot

                Screen.LikedPhotosDetail::class.simpleName -> return Screen.LikedPhotosDetail(id = -1)
                Screen.LikedPhotosList::class.simpleName -> return Screen.LikedPhotosList
                Screen.LikedPhotosRoot::class.simpleName -> return Screen.LikedPhotosRoot

                else -> return null
            }
        }
    return null
}

