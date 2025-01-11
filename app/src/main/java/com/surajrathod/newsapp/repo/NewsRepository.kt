package com.surajrathod.newsapp.repo

import com.surajrathod.newsapp.api.ApiService
import com.surajrathod.newsapp.api.BaseApiResponse
import com.surajrathod.newsapp.api.NetworkResult
import com.surajrathod.newsapp.data.NewsResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NewsRepository @Inject constructor(private val apiService: ApiService): BaseApiResponse() {

    suspend fun getArticles(): Flow<NetworkResult<NewsResponse>> = flow {
        emit(
            safeApiCall {
                apiService.getTopHeadlines()
            }
        )
    }

}