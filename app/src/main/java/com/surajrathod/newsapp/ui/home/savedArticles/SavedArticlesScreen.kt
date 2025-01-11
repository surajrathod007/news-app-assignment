package com.surajrathod.newsapp.ui.home.savedArticles

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.surajrathod.newsapp.data.Article
import com.surajrathod.newsapp.data.dummyArticles
import com.surajrathod.newsapp.ui.components.FullScreenError
import com.surajrathod.newsapp.ui.components.FullScreenLoader
import com.surajrathod.newsapp.ui.components.NewsCard
import com.surajrathod.newsapp.ui.home.savedArticles.viewmodel.SavedArticlesViewModel

sealed class SavedArticlesScreenState<out T> {
    data object Loading : SavedArticlesScreenState<Nothing>()
    data class Success<out T>(val data: T) : SavedArticlesScreenState<T>()
    data class Error(val message: String) : SavedArticlesScreenState<Nothing>()
}

@Composable
fun SavedArticlesScreen() {

    val savedArticlesViewModel: SavedArticlesViewModel = hiltViewModel()
    val savedArticlesScreenState = savedArticlesViewModel.savedArticleScreenState.collectAsState()

    LaunchedEffect(Unit) {
        savedArticlesViewModel.loadFavArticles()
    }

    SavedArticlesScreenUi(
        savedArticlesScreenState = savedArticlesScreenState.value,
        onDeleteClick = { article ->
            savedArticlesViewModel.removeArticle(article)
        })

}

@Composable
fun SavedArticlesScreenUi(
    savedArticlesScreenState: SavedArticlesScreenState<List<Article>>,
    onDeleteClick: (Article) -> Unit = {}
) {

    Box(modifier = Modifier.fillMaxSize()) {

        when (savedArticlesScreenState) {

            is SavedArticlesScreenState.Success -> {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    contentPadding = PaddingValues(vertical = 12.dp)
                ) {
                    items(savedArticlesScreenState.data, key = { it.url }) { article ->
                        NewsCard(
                            showDeleteIcon = true,
                            imageUrl = article.urlToImage ?: "",
                            headline = article.title ?: "",
                            description = article.description ?: "",
                            onReadMoreClick = {

                            },
                            onDeleteClick = {
                                onDeleteClick.invoke(article)
                            })
                    }
                }
            }

            is SavedArticlesScreenState.Error -> {
                FullScreenError(savedArticlesScreenState.message)
            }

            SavedArticlesScreenState.Loading -> {
                FullScreenLoader()
            }

        }

    }

}

@Composable
@Preview
fun SavedArticleScreenUiPreview() {
    MaterialTheme {
        SavedArticlesScreenUi(savedArticlesScreenState = SavedArticlesScreenState.Success(dummyArticles))
    }
}