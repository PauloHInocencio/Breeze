package br.com.noartcode.breeze.android.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AllInclusive
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector


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
        icon = Icons.Filled.Favorite,
        route = Screen.LikedPhotosRoot
    )
)