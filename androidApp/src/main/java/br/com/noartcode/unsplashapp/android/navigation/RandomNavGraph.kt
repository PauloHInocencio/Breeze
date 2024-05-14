@file:OptIn(ExperimentalSharedTransitionApi::class)

package br.com.noartcode.unsplashapp.android.navigation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import br.com.noartcode.unsplashapp.android.presentation.detail.PhotosDetailEvent
import br.com.noartcode.unsplashapp.android.presentation.detail.PhotosDetailScreen
import br.com.noartcode.unsplashapp.android.presentation.detail.PhotosDetailViewModel
import br.com.noartcode.unsplashapp.android.presentation.random.RandomPhotosScreen
import br.com.noartcode.unsplashapp.android.presentation.random.RandomPhotosViewModel


@Composable
fun RandomNavGraph(
     navController:NavHostController,
     modifier: Modifier = Modifier
) {
    SharedTransitionLayout {
        NavHost(
            navController = navController,
            startDestination = Screen.Random.List::class,
            route = Screen.Random::class,
            modifier = modifier
        ) {

            composable<Screen.Random.List>{
                val viewModel = hiltViewModel<RandomPhotosViewModel>()
                RandomPhotosScreen(
                    state = viewModel.state.collectAsState().value,
                    onEvent = viewModel::onEvent,
                    onNavigateToDetail = { id ->
                        navController.navigate(Screen.Random.Detail(id))
                    },
                    animatedVisibilityScope = this
                )
            }

            composable<Screen.Random.Detail>{ backStackEntry ->
                val id = backStackEntry.toRoute<Screen.Random.Detail>().id
                val viewModel = hiltViewModel<PhotosDetailViewModel>()
                PhotosDetailScreen(
                    state = viewModel.state.collectAsState().value,
                    animatedVisibilityScope = this
                )
                LaunchedEffect(Unit) {
                    viewModel.onEvent(PhotosDetailEvent.OnLoadPhoto(id))
                }

            }
        }
    }
}

/*@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = ChildScreen.valueOf(
        navBackStackEntry?.destination?.route?.substringBefore("?") ?: ChildScreen.ChildRandomPhotos.name
    )

    Scaffold(
        bottomBar = {
            if (currentScreen != ChildScreen.PhotosDetail) {
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


        SharedTransitionLayout {
            NavHost(
                navController = navController,
                startDestination = ChildScreen.ChildRandomPhotos.name,
                modifier = Modifier.padding(paddingValues)
            ) {

                composable(route = ChildScreen.ChildRandomPhotos.name) {
                    val viewModel = hiltViewModel<RandomPhotosViewModel>()
                    RandomPhotosScreen(
                        state = viewModel.state.collectAsState().value,
                        onEvent = viewModel::onEvent,
                        onNavigateToDetail = { id ->
                            navController.navigate("${ChildScreen.PhotosDetail.name}?photoId=$id")
                        },
                        animatedVisibilityScope = this
                    )
                }

                composable(route = ChildScreen.ChildSearchPhotos.name) {
                    SearchPhotosScreen()
                }

                composable(route = ChildScreen.ChildLikedPhotos.name) {
                    PhotosCollectionScreen()
                }

                composable(
                    route = "${ChildScreen.PhotosDetail.name}?photoId={photoId}",
                    arguments = listOf(navArgument("photoId") { type = NavType.LongType})
                ) { backStackEntry ->
                    val id = checkNotNull(backStackEntry.arguments?.getLong("photoId"))
                    val viewModel = hiltViewModel<PhotosDetailViewModel>()
                    PhotosDetailScreen(
                        state = viewModel.state.collectAsState().value,
                        animatedVisibilityScope = this
                    )
                    LaunchedEffect(Unit) {
                        viewModel.onEvent(PhotosDetailEvent.OnLoadPhoto(id))
                    }

                }
            }
        }


    }
}*/