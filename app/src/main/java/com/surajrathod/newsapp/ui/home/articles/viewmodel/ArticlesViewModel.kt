package com.surajrathod.newsapp.ui.home.articles.viewmodel

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.surajrathod.newsapp.api.NetworkResult
import com.surajrathod.newsapp.data.Article
import com.surajrathod.newsapp.repo.NewsRepository
import com.surajrathod.newsapp.ui.home.articles.ArticlesScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticlesViewModel @Inject constructor(private val newsRepository: NewsRepository) : ViewModel(){

    init {
        loadArticles()
    }

    private val _articlesScreenState = MutableStateFlow<ArticlesScreenState<List<Article>>>(ArticlesScreenState.Loading)
    val articlesScreenState: StateFlow<ArticlesScreenState<List<Article>>> = _articlesScreenState


    fun loadArticles() = viewModelScope.launch(Dispatchers.IO) {
        _articlesScreenState.value = ArticlesScreenState.Loading
        newsRepository.getArticles().collect{
            when(it){
                is NetworkResult.Success -> {
                    val articles = it.data?.articles;
                    if(!articles.isNullOrEmpty()){
                        _articlesScreenState.value = ArticlesScreenState.Success(articles)
                    }else{
                        _articlesScreenState.value = ArticlesScreenState.Error("No articles found !")
                    }
                }
                is NetworkResult.Error -> {
                    _articlesScreenState.value = ArticlesScreenState.Error(it.errorResponse?.errorMessage?:"")
                }
            }
        }
    }

}