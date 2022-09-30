package com.surodeev.currenciestestapplication.ui.widget

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.surodeev.currenciestestapplication.entity.BottomNavigationItem
import com.surodeev.currenciestestapplication.ui.theme.Secondary

@Composable
fun BottomNavigationBar(navController: NavController) {
    val pages = listOf(
        BottomNavigationItem.Popular,
        BottomNavigationItem.Favorites,
    )

    BottomNavigation {
        pages.forEachIndexed { _, page ->
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination?.route

            BottomNavigationItem(
                selected = currentDestination == page.route,
                onClick = {
                    navController.navigate(page.route) {
                        navController.graph.startDestinationRoute?.let { screen_route ->
                            popUpTo(screen_route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                selectedContentColor = Secondary,
                unselectedContentColor = Color.Black.copy(alpha = 0.5f),
                icon = {
                    Icon(
                        imageVector = page.icon,
                        contentDescription = page.title,
                        modifier = Modifier
                            .size(56.dp)
                            .padding(16.dp)
                    )
                })
        }
    }
}