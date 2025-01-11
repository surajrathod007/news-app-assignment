package com.surajrathod.newsapp.ui.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination

@Composable
fun BottomNavigationBar(navController: NavController, items: List<BottomNavItem>) {
    val currentRoute = getCurrentRoute(navController = navController)
    NavigationBar {
        items.forEach { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = if (currentRoute == item.route) item.filledIcon else item.borderIcon,
                        contentDescription = item.label
                    )
                },
                label = { Text(text = item.label) },
                selected = currentRoute == item.route,
                onClick = {
                    //prevent navigate to same screen if again pressed
                    if (currentRoute != item.route) {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )
        }
    }
}

