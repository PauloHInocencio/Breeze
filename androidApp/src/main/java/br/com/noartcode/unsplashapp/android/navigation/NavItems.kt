package br.com.noartcode.unsplashapp.android.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AllInclusive
import androidx.compose.material.icons.filled.Collections
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

data class NavItem(
    val label:String,
    val icon: ImageVector,
    val route: String
)


val listOfNavItems = listOf(
    NavItem(
        label = "Random Photos",
        icon = Icons.Default.AllInclusive,
        route = Screens.RandomPhotos.name
    ),
    NavItem(
        label = "Search Photos",
        icon = Icons.Default.Search,
        route = Screens.SearchPhotos.name
    ),
    NavItem(
        label = "Photos Collections",
        icon = Icons.Default.Collections,
        route = Screens.PhotosCollections.name
    )
)