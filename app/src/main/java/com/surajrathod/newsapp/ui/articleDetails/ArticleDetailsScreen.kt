package com.surajrathod.newsapp.ui.articleDetails

import android.webkit.WebView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import com.surajrathod.newsapp.data.Article

@Composable
fun ArticleDetailsScreen(article: Article,onBackPressed: ()-> Unit){
    Scaffold(
        topBar = {
           TopAppBar(title = {
               Text(text = article.title?:"", overflow = TextOverflow.Ellipsis, maxLines = 1)
           }, navigationIcon = {
               IconButton(onClick = {
                   onBackPressed.invoke()
               }) {
                   Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null )
               }
           })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {  }) {
                Icon(imageVector = Icons.Default.FavoriteBorder, contentDescription = null )
            }
        }
    ) { paddingValues ->
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)){

            WebViewScreen(article = article)
        }
    }

}