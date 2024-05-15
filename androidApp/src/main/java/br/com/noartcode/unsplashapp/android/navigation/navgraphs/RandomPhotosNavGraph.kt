@file:OptIn(ExperimentalSharedTransitionApi::class)

package br.com.noartcode.unsplashapp.android.navigation.navgraphs

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import br.com.noartcode.unsplashapp.android.navigation.Screen
import br.com.noartcode.unsplashapp.android.presentation.detail.PhotosDetailEvent
import br.com.noartcode.unsplashapp.android.presentation.detail.PhotosDetailScreen
import br.com.noartcode.unsplashapp.android.presentation.detail.PhotosDetailViewModel
import br.com.noartcode.unsplashapp.android.presentation.random.RandomPhotosScreen
import br.com.noartcode.unsplashapp.android.presentation.random.RandomPhotosViewModel


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun RandomPhotosNavGraph(
    navController: NavHostController = rememberNavController()
) {
    SharedTransitionLayout {
        NavHost(navController = navController,
            startDestination = Screen.RandomPhotosList
        ) {
            composable<Screen.RandomPhotosList>{
                val viewModel = hiltViewModel<RandomPhotosViewModel>()
                RandomPhotosScreen(
                    state = viewModel.state.collectAsState().value,
                    onEvent = viewModel::onEvent,
                    onNavigateToDetail = { id ->
                        navController.navigate(Screen.RandomPhotosDetail(id))
                    },
                    animatedVisibilityScope = this
                )
            }

            composable<Screen.RandomPhotosDetail>{ backStackEntry ->
                val id = backStackEntry.toRoute<Screen.RandomPhotosDetail>().id
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