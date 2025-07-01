package com.example.examplea.util

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screens(val title:String, val icon:ImageVector) {
    object DashBoard : Screens("Dashboard", icon = Icons.Default.Home)
    object Tasks : Screens("Tasks", icon = Icons.Default.List)
    object Completed :Screens("Completed", icon = Icons.Default.Done)
    object Settings: Screens("Settings", icon = Icons.Default.Settings)
}