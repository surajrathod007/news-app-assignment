package com.surajrathod.newsapp.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.surajrathod.newsapp.ui.home.articles.ArticlesScreen
import com.surajrathod.newsapp.ui.navigation.BottomNavItem

@Composable
fun HomeScreen() {
    val items = listOf(BottomNavItem.Articles, BottomNavItem.Saved)

    // State to track the selected tab
    var selectedTab by remember { mutableStateOf(items[0].route) }

    Scaffold(
        bottomBar = {
            NavigationBar {
                items.forEach { item ->
                    NavigationBarItem(
                        icon = { Icon(imageVector = item.icon, contentDescription = item.label) },
                        label = { Text(text = item.label) },
                        selected = selectedTab == item.route,
                        onClick = { selectedTab = item.route }
                    )
                }
            }
        }
    ) { innerPadding ->
        // Content based on selected tab
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            when (selectedTab) {
                BottomNavItem.Articles.route -> ArticlesScreen()
                BottomNavItem.Saved.route -> SavedScreen()
            }
        }
    }
}


// Saved Tab Screen
@Composable
fun SavedScreen() {
    Text(text = "Saved Screen", style = MaterialTheme.typography.h2)
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    MaterialTheme {
        HomeScreen()
    }
}