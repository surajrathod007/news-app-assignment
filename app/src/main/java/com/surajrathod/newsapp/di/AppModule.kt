package com.surajrathod.newsapp.di

import android.app.Application
import com.surajrathod.newsapp.api.ApiService
import com.surajrathod.newsapp.api.NetworkConnectionInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideBaseUrl() = "https://newsapi.org/v2/"

    @Singleton
    @Provides
    fun provideOkHttpClient(application: Application): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder().apply {
            this.readTimeout(30, TimeUnit.SECONDS)
            this.connectTimeout(30, TimeUnit.SECONDS)
            this.writeTimeout(30, TimeUnit.SECONDS)
            this.addInterceptor(NetworkConnectionInterceptor(application))
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            addInterceptor(loggingInterceptor)
            this.addInterceptor(NetworkConnectionInterceptor(application))
            addInterceptor {chain->
                val originalRequest = chain.request()
                val apiKey = "8651796c338a4da1af175b8545f380ca"
                val newRequest = originalRequest.newBuilder().apply {
                    header("Authorization", apiKey)
                }.build()
                chain.proceed(newRequest)
            }
        }.build()
    }


    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        baseUrl: String
    ): Retrofit =
        Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(baseUrl)
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit) = retrofit.create(ApiService::class.java)


}