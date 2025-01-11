package com.surajrathod.newsapp.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.surajrathod.newsapp.data.Article

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticle(article: Article)

    @Query("SELECT * FROM articles WHERE url = :url")
    suspend fun getArticleByUrl(url: String): Article?

    @Query("SELECT * FROM articles")
    suspend fun getAllArticles(): List<Article>

    @Query("DELETE FROM articles WHERE url = :url")
    suspend fun deleteArticleByUrl(url: String)

    @Query("DELETE FROM articles")
    suspend fun clearAllArticles()
}