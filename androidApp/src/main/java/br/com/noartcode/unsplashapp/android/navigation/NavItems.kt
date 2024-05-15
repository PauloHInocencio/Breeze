package br.com.noartcode.unsplashapp.android.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AllInclusive
import androidx.compose.material.icons.filled.HeartBroken
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import kotlin.reflect.KClass


data class NavItem(
    val label:String,
    val icon: ImageVector,
    val route: Screen
)


val listOfNavItems = listOf(
    NavItem(
        label = "Random Photos",
        icon = Icons.Default.AllInclusive,
        route = Screen.RandomPhotosRoot
    ),
    NavItem(
        label = "Search Photos",
        icon = Icons.Default.Search,
        route = Screen.SearchedPhotosRoot
    ),
    NavItem(
        label = "Liked Photos",
        icon = Icons.Filled.HeartBroken,
        route = Screen.LikedPhotosRoot
    )
)