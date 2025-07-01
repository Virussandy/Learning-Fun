package com.mollosradix.deals.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.List
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavigationItem(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Home : NavigationItem(
        route = "home",
        title = "Home",
        icon = Icons.Rounded.Home
    )
    
    object Notifications : NavigationItem(
        route = "notifications",
        title = "Notifications",
        icon = Icons.Rounded.Notifications
    )
    
    object Profile : NavigationItem(
        route = "profile",
        title = "Profile",
        icon = Icons.Rounded.Person
    )

    object Search : NavigationItem(
        route = "search",
        title = "Search",
        icon = Icons.Rounded.Search
    )

    companion object {
        fun values() = listOf(Home, Notifications, Profile)
    }
} 