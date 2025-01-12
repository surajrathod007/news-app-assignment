package com.surajrathod.newsapp.ui.articleDetails

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.surajrathod.newsapp.data.Article

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleDetailsScreen(article: Article, onBackPressed: () -> Unit) {

    val articlesViewModel: ArticleDetailsViewModel = hiltViewModel()
    val isFav = articlesViewModel.isArticleExists.collectAsState().value
    val isArticleSavingOffline = articlesViewModel.isArticleSavingOffline.collectAsState().value

    LaunchedEffect (Unit) {
        articlesViewModel.checkArticleExists(article.url)
    }

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(text = article.title ?: "", overflow = TextOverflow.Ellipsis, maxLines = 1)
            }, navigationIcon = {
                IconButton(onClick = {
                    onBackPressed.invoke()
                }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null
                    )
                }
            })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                if(isArticleSavingOffline){
                    return@FloatingActionButton     //prevent saving when saving is in progress
                }
                if(isFav){
                    articlesViewModel.removeArticle(article)
                }else{
                    articlesViewModel.saveArticle(article)
                }
            }) {
                if(isArticleSavingOffline){
                    CircularProgressIndicator(modifier = Modifier.size(18.dp), strokeWidth = 2.dp)
                }else{
                    Icon(
                        imageVector = if (isFav) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = null
                    )
                }
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {

            WebViewScreen(article = article)
        }
    }

}