package br.com.noartcode.unsplashapp.android.navigation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AllInclusive
import androidx.compose.material.icons.filled.HeartBroken
import androidx.compose.material.icons.filled.Search
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
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import androidx.navigation.toRoute
import br.com.noartcode.unsplashapp.android.presentation.detail.PhotosDetailEvent
import br.com.noartcode.unsplashapp.android.presentation.detail.PhotosDetailScreen
import br.com.noartcode.unsplashapp.android.presentation.detail.PhotosDetailViewModel
import br.com.noartcode.unsplashapp.android.presentation.random.RandomPhotosScreen
import br.com.noartcode.unsplashapp.android.presentation.random.RandomPhotosViewModel

@Composable
fun AppNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {

    Scaffold(
        bottomBar = {
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
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screen.Random::class,
            modifier = modifier.padding(paddingValues)
        ) {

            composable<Screen.Random> {
                RandomNavGraph()
            }

            composable<Screen.Search> {
                //SearchNavGraph(navController = navController)
            }

            composable<Screen.Linked> {
               // LikedNavGraph(navController = navController)
            }

        }
    }
}


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun RandomNavGraph(
    navController: NavHostController = rememberNavController()
) {
    SharedTransitionLayout {
        NavHost(navController = navController,
            startDestination = Screen.Random.List
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
