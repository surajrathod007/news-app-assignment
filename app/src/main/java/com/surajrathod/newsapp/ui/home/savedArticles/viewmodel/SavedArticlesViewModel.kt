package com.surajrathod.newsapp.ui.home.savedArticles.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.surajrathod.newsapp.api.NetworkResult
import com.surajrathod.newsapp.data.Article
import com.surajrathod.newsapp.room.ArticleDao
import com.surajrathod.newsapp.ui.home.articles.ArticlesScreenState
import com.surajrathod.newsapp.ui.home.savedArticles.SavedArticlesScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SavedArticlesViewModel @Inject constructor(private val articleDao: ArticleDao) : ViewModel() {

    private val _savedArticleScreenSTate =
        MutableStateFlow<SavedArticlesScreenState<List<Article>>>(
            SavedArticlesScreenState.Loading
        )
    val savedArticleScreenState: StateFlow<SavedArticlesScreenState<List<Article>>> =
        _savedArticleScreenSTate

    fun loadFavArticles(showLoader: Boolean = false) = viewModelScope.launch(Dispatchers.IO) {
        if (showLoader)
            _savedArticleScreenSTate.value = SavedArticlesScreenState.Loading

        val articles = articleDao.getAllArticles() //can be filtered only fav
        if (articles.isNotEmpty()) {
            _savedArticleScreenSTate.value = SavedArticlesScreenState.Success(articles)
        } else {
            _savedArticleScreenSTate.value =
                SavedArticlesScreenState.Error("No articles found in favourites!")
        }
    }

    fun removeArticle(article: Article) = viewModelScope.launch(Dispatchers.IO) {
        articleDao.deleteArticleByUrl(article.url)
        loadFavArticles(showLoader = false)
    }

}