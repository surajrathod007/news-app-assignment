package com.surajrathod.newsapp.ui.home

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.surajrathod.newsapp.data.Article
import com.surajrathod.newsapp.ui.articleDetails.ArticleDetailsScreen
import com.surajrathod.newsapp.ui.home.articles.ArticlesScreen
import com.surajrathod.newsapp.ui.home.savedArticles.SavedArticlesScreen
import com.surajrathod.newsapp.ui.navigation.ArticleDetailsRoute
import com.surajrathod.newsapp.ui.navigation.ArticleDetailsRouteNavType
import com.surajrathod.newsapp.ui.navigation.BottomNavItem
import com.surajrathod.newsapp.ui.navigation.BottomNavigationBar
import com.surajrathod.newsapp.ui.navigation.getCurrentRoute
import kotlin.reflect.typeOf

@Composable
fun HomeScreen() {

    val supportedRoutes = listOf(BottomNavItem.Articles.route, BottomNavItem.Saved.route)
    val navController = rememberNavController()
    val items = listOf(BottomNavItem.Articles, BottomNavItem.Saved)

    Scaffold(
        bottomBar = {
            if (getCurrentRoute(navController = navController) in supportedRoutes)
                BottomNavigationBar(navController = navController, items = items)
        }
    ) { innerPadding ->
        NavHost(
            modifier = Modifier.padding(innerPadding),
            navController = navController,
            startDestination = BottomNavItem.Articles.route,
            enterTransition = {
                EnterTransition.None
            },
            exitTransition = {
                ExitTransition.None
            }
        ) {

            composable(BottomNavItem.Articles.route) {
                ArticlesScreen(onReadMoreClick = { article ->
                    navController.navigate(ArticleDetailsRoute(article = article))
                })
            }

            composable(BottomNavItem.Saved.route) {
                SavedArticlesScreen()
            }

            composable<ArticleDetailsRoute>(
                typeMap = mapOf(typeOf<Article>() to ArticleDetailsRouteNavType)
            ) {
                val articleDetailsRoute = it.toRoute<ArticleDetailsRoute>()
                ArticleDetailsScreen(article = articleDetailsRoute.article, onBackPressed = {
                    navController.popBackStack()
                })
            }

        }
    }
}
