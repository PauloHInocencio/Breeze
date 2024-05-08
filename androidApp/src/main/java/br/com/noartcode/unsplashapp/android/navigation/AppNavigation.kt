package br.com.noartcode.unsplashapp.android.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import br.com.noartcode.unsplashapp.android.presentation.collection.PhotosCollectionScreen
import br.com.noartcode.unsplashapp.android.presentation.detail.PhotosDetailEvent
import br.com.noartcode.unsplashapp.android.presentation.detail.PhotosDetailScreen
import br.com.noartcode.unsplashapp.android.presentation.detail.PhotosDetailViewModel
import br.com.noartcode.unsplashapp.android.presentation.random.RandomPhotosScreen
import br.com.noartcode.unsplashapp.android.presentation.random.RandomPhotosViewModel
import br.com.noartcode.unsplashapp.android.presentation.search.SearchPhotosScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = Screens.valueOf(
        navBackStackEntry?.destination?.route?.substringBefore("?") ?: Screens.RandomPhotos.name
    )

    Scaffold(
        bottomBar = {
            if (currentScreen != Screens.PhotosDetail) {
                NavigationBar {
                    listOfNavItems.forEach { navItem ->
                        NavigationBarItem(
                            selected = navBackStackEntry?.destination?.hierarchy?.any { it.route == navItem.route } == true,
                            onClick = {
                                navController.navigate(navItem.route) {
                                    popUpTo(navController.graph.findStartDestination().id)
                                    launchSingleTop = true
                                }
                            },
                            icon = { Icon(imageVector = navItem.icon, contentDescription = null) },
                            label = {
                                Text(text = navItem.label)
                            }
                        )
                    }
                }
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screens.RandomPhotos.name,
            modifier = Modifier.padding(paddingValues)
        ) {

            composable(route = Screens.RandomPhotos.name) {
                val viewModel = hiltViewModel<RandomPhotosViewModel>()
                RandomPhotosScreen(
                    state = viewModel.state.collectAsState().value,
                    onEvent = viewModel::onEvent,
                    onNavigateToDetail = { id ->
                       navController.navigate("${Screens.PhotosDetail.name}?photoId=$id")
                    }
                )
            }

            composable(route = Screens.SearchPhotos.name) {
                SearchPhotosScreen()
            }

            composable(route = Screens.PhotosCollections.name) {
                PhotosCollectionScreen()
            }

            composable(
                route = "${Screens.PhotosDetail.name}?photoId={photoId}",
                arguments = listOf(navArgument("photoId") { type = NavType.LongType})
            ) { backStackEntry ->
                val id = checkNotNull(backStackEntry.arguments?.getLong("photoId"))
                val viewModel = hiltViewModel<PhotosDetailViewModel>()
                PhotosDetailScreen(
                    state = viewModel.state.collectAsState().value,
                )

                LaunchedEffect(Unit) {
                    viewModel.onEvent(PhotosDetailEvent.OnLoadPhoto(id))
                }

            }
        }
    }
}