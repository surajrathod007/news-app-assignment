package com.surajrathod.newsapp.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.surajrathod.newsapp.data.Article

@Database(
    entities = [Article::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(SourceConverter::class)
abstract class ArticleDatabase : RoomDatabase() {

    abstract fun articleDao() : ArticleDao
}