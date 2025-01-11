package com.surajrathod.newsapp.ui.articleDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.surajrathod.newsapp.data.Article
import com.surajrathod.newsapp.room.ArticleDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticleDetailsViewModel @Inject constructor(private val articleDao: ArticleDao) :
    ViewModel() {

    private val _isArticleExists = MutableStateFlow(false)
    val isArticleExists: StateFlow<Boolean> = _isArticleExists

    fun checkArticleExists(url: String) = viewModelScope.launch(Dispatchers.IO) {
        val article = articleDao.getArticleByUrl(url)
        _isArticleExists.value = article != null
    }

    fun saveArticle(article: Article) = viewModelScope.launch(Dispatchers.IO) {
        articleDao.insertArticle(article)
        checkArticleExists(article.url) //refresh the fav state
    }

    fun removeArticle(article: Article) = viewModelScope.launch(Dispatchers.IO) {
        articleDao.deleteArticleByUrl(article.url)
        checkArticleExists(article.url) //refresh the fav state
    }

}