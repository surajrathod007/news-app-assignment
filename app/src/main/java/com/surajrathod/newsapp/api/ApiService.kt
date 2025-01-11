package com.surajrathod.newsapp.api

import com.surajrathod.newsapp.data.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("top-headlines")
    suspend fun getTopHeadlines(@Query("country") country: String = "us") : Response<NewsResponse>

}