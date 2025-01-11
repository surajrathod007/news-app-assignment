package com.surajrathod.newsapp.di

import android.content.Context
import androidx.room.Room
import com.surajrathod.newsapp.room.ArticleDao
import com.surajrathod.newsapp.room.ArticleDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideArticleDatabase(
        @ApplicationContext appContext: Context,
    ): ArticleDatabase {
        return Room.databaseBuilder(
            appContext,
            ArticleDatabase::class.java,
            "article_db"
        ).build()
    }


    @Provides
    @Singleton
    fun provideArticleDao(articleDatabase: ArticleDatabase): ArticleDao {
        return articleDatabase.articleDao()
    }

}