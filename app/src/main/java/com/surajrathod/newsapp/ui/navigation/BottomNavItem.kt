package com.surajrathod.newsapp.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    val label: String,
    val filledIcon: ImageVector,
    val borderIcon: ImageVector,
    val route: String
) {
    data object Articles : BottomNavItem(
        label = "Home",
        filledIcon = Icons.Filled.Home,
        borderIcon = Icons.Outlined.Home,
        route = "home"
    )

    data object Saved : BottomNavItem(
        label = "Saved",
        filledIcon = Icons.Filled.Favorite,
        borderIcon = Icons.Outlined.FavoriteBorder,
        route = "saved"
    )
}