package com.surajrathod.newsapp.ui.home.articles

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.surajrathod.newsapp.data.Article
import com.surajrathod.newsapp.data.dummyArticles
import com.surajrathod.newsapp.ui.components.FullScreenError
import com.surajrathod.newsapp.ui.components.FullScreenLoader
import com.surajrathod.newsapp.ui.components.NewsCard
import com.surajrathod.newsapp.ui.home.articles.viewmodel.ArticlesViewModel

sealed class ArticlesScreenState<out T> {
    data object Loading : ArticlesScreenState<Nothing>()
    data class Success<out T>(val data: T) : ArticlesScreenState<T>()
    data class Error(val message: String) : ArticlesScreenState<Nothing>()
}

@Composable
fun ArticlesScreen(onReadMoreClick: (Article) -> Unit) {

    val articlesViewModel: ArticlesViewModel = hiltViewModel()
    val articlesUiState = articlesViewModel.articlesScreenState.collectAsState()
    ArticlesScreenUi(articleScreenState = articlesUiState.value, onReadMoreClick = onReadMoreClick, onPullToRefresh = {
        articlesViewModel.loadArticles()
    })
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ArticlesScreenUi(
    articleScreenState: ArticlesScreenState<List<Article>>,
    onReadMoreClick: (Article) -> Unit = {},
    onPullToRefresh: () -> Unit = {}
) {

    val pullRefreshState = rememberPullRefreshState(false, { onPullToRefresh.invoke() })

    Box(modifier = Modifier.fillMaxSize().pullRefresh(pullRefreshState)) {
        when (articleScreenState) {
            is ArticlesScreenState.Success -> {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    contentPadding = PaddingValues(vertical = 12.dp)
                ) {
                    items(articleScreenState.data, key = { it.url }) { article ->
                        NewsCard(
                            imageUrl = article.urlToImage ?: "",
                            headline = article.title ?: "",
                            description = article.description ?: "",
                            onReadMoreClick = {
                                onReadMoreClick.invoke(article)
                            },
                            onDeleteClick = {

                            })
                    }
                }
            }

            is ArticlesScreenState.Error -> {
                FullScreenError(articleScreenState.message, errorButtonText = "Retry", onErrorButtonClick = {
                    onPullToRefresh.invoke()
                })
            }

            ArticlesScreenState.Loading -> {
                FullScreenLoader()
            }

        }
        PullRefreshIndicator(
            contentColor = MaterialTheme.colorScheme.primary,
            refreshing = false,
            state = pullRefreshState,
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }

}

@Composable
@Preview
fun ArticleScreenUiPreview() {
    MaterialTheme {
        ArticlesScreenUi(articleScreenState = ArticlesScreenState.Success(dummyArticles))
    }
}