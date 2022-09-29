package com.surodeev.currenciestestapplication.entity

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.List
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavigationItem(var title: String, var icon: ImageVector, var route: String) {

    object Popular : BottomNavigationItem("Popular", Icons.Filled.List, "popular")
    object Favorites : BottomNavigationItem("Favorites", Icons.Filled.Favorite, "favorites")
}