package com.example.bottomnavigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavigationScreen(
    var route:String,
    var title:String,
    var icon:ImageVector
){
    object HomeScreen : BottomNavigationScreen(
        route = "home",
        title = "Home",
        icon = Icons.Default.Home
    )

    object ProfileScreen : BottomNavigationScreen(
        route = "profile",
        title = "Profile",
        icon = Icons.Default.Person
    )

    object Setting : BottomNavigationScreen(
        route = "setting",
        title = "Setting",
        icon = Icons.Default.Settings
    )
}
