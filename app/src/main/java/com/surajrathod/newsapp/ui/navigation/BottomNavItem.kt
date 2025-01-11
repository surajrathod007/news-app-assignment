package com.surajrathod.newsapp.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    val label: String,
    val icon: ImageVector,
    val route: String
) {
    data object Articles : BottomNavItem(
        label = "Home",
        icon = Icons.Default.Home, // Replace with an appropriate icon
        route = "home"
    )

    data object Saved : BottomNavItem(
        label = "Saved",
        icon = Icons.Default.FavoriteBorder, // Replace with an appropriate icon
        route = "saved"
    )
}