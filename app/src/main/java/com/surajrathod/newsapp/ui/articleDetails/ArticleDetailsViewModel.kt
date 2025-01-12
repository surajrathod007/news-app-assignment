package com.surajrathod.newsapp.ui.articleDetails

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.surajrathod.newsapp.data.Article
import com.surajrathod.newsapp.room.ArticleDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.File
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class ArticleDetailsViewModel @Inject constructor(
    private val articleDao: ArticleDao,
    private val mApp: Application
) :
    AndroidViewModel(mApp) {

    private val _isArticleExists = MutableStateFlow(false)
    val isArticleExists: StateFlow<Boolean> = _isArticleExists

    private val _isArticleSavingOffline = MutableStateFlow(false)
    val isArticleSavingOffline: StateFlow<Boolean> = _isArticleSavingOffline

    fun checkArticleExists(url: String) = viewModelScope.launch(Dispatchers.IO) {
        val article = articleDao.getArticleByUrl(url)
        _isArticleExists.value = article != null
    }

    fun saveArticle(article: Article) = viewModelScope.launch(Dispatchers.IO) {
        _isArticleSavingOffline.value = true
        articleDao.insertArticle(article)
        saveHtmlOffline(context = mApp, article.url, article.getHtmlFileName())
        _isArticleSavingOffline.value = false
        checkArticleExists(article.url) //refresh the fav state
    }

    fun removeArticle(article: Article) = viewModelScope.launch(Dispatchers.IO) {
        articleDao.deleteArticleByUrl(article.url)
        deleteHtmlOffline(context = mApp, article.getHtmlFileName())
        checkArticleExists(article.url) //refresh the fav state
    }

    private fun saveHtmlOffline(context: Context, url: String, fileName: String) {
        val client = OkHttpClient()
        val request = Request.Builder().url(url).build()

        val response = client.newCall(request).execute()
        if (response.isSuccessful) {
            val htmlContent = response.body?.string()
            htmlContent?.let {
                val file = File(context.filesDir, fileName)
                file.writeText(it)
            }
        } else {
            throw IOException("Failed to fetch HTML")
        }
    }

    private fun deleteHtmlOffline(context: Context, fileName: String) {
        val file = File(context.filesDir, fileName)
        if (file.exists()) {
            file.delete()
        }
    }

}