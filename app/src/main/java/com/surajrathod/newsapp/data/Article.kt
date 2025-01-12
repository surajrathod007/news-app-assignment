package com.surajrathod.newsapp.data

import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavType
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.surajrathod.newsapp.room.SourceConverter
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Entity(tableName = "articles")
@Serializable
@Parcelize
data class Article(
    @PrimaryKey val url: String,
    val author: String? = null,
    val content: String? = null,
    val description: String? = null,
    val publishedAt: String? = null,
    @TypeConverters(SourceConverter::class)
    val source: Source? = null,
    val title: String? = null,
    val urlToImage: String? = null,
    val isFavourite: Boolean = false
) : Parcelable{

    fun getHtmlFileName() : String {
        return Uri.encode(url)+".html";
    }

}



val dummyArticles = listOf(
    Article(
        author = "John Doe",
        content = "This is the content of the article written by John Doe.",
        description = "An article discussing the latest trends in technology.",
        publishedAt = "2025-01-11T12:00:00Z",
        source = Source(id = "tech-news", name = "Tech News"),
        title = "Latest Trends in Technology",
        url = "https://example.com/articles/1",
        urlToImage = "https://example.com/images/article1.jpg"
    ),
    Article(
        author = "Jane Smith",
        content = "A comprehensive guide to improving productivity.",
        description = "Learn how to improve your productivity with these simple tips.",
        publishedAt = "2025-01-10T15:30:00Z",
        source = Source(id = "life-hacks", name = "Life Hacks"),
        title = "Boost Your Productivity",
        url = "https://example.com/articles/2",
        urlToImage = "https://example.com/images/article2.jpg"
    ),
    Article(
        author = "Alice Johnson",
        content = "Discover the secrets behind a healthy lifestyle.",
        description = "Tips and tricks to live a healthier life.",
        publishedAt = "2025-01-09T09:45:00Z",
        source = Source(id = "health-today", name = "Health Today"),
        title = "Secrets to a Healthy Lifestyle",
        url = "https://example.com/articles/3",
        urlToImage = "https://example.com/images/article3.jpg"
    ),
    Article(
        author = "Bob Williams",
        content = "An in-depth analysis of the recent stock market trends.",
        description = "How the stock market is evolving in 2025.",
        publishedAt = "2025-01-08T11:20:00Z",
        source = Source(id = "finance-world", name = "Finance World"),
        title = "Stock Market Trends of 2025",
        url = "https://example.com/articles/4",
        urlToImage = "https://example.com/images/article4.jpg"
    ),
    Article(
        author = "Emily Brown",
        content = "A beginner's guide to learning programming.",
        description = "Start your journey into programming with these resources.",
        publishedAt = "2025-01-07T18:00:00Z",
        source = Source(id = "coding-daily", name = "Coding Daily"),
        title = "Learn Programming: A Beginner's Guide",
        url = "https://example.com/articles/5",
        urlToImage = "https://example.com/images/article5.jpg"
    ),
    Article(
        author = "Chris Green",
        content = "Exploring the beauty of nature through photography.",
        description = "Stunning photographs that capture the beauty of nature.",
        publishedAt = "2025-01-06T14:15:00Z",
        source = Source(id = "photo-journal", name = "Photo Journal"),
        title = "The Beauty of Nature in Pictures",
        url = "https://example.com/articles/6",
        urlToImage = "https://example.com/images/article6.jpg"
    ),
    Article(
        author = "Sophia Davis",
        content = "Everything you need to know about the latest smartphone.",
        description = "A detailed review of the newest smartphone on the market.",
        publishedAt = "2025-01-05T20:50:00Z",
        source = Source(id = "gadget-review", name = "Gadget Review"),
        title = "Latest Smartphone Review",
        url = "https://example.com/articles/7",
        urlToImage = "https://example.com/images/article7.jpg"
    ),
    Article(
        author = "Michael Lee",
        content = "An exploration of ancient civilizations and their cultures.",
        description = "Uncover the mysteries of ancient civilizations.",
        publishedAt = "2025-01-04T10:40:00Z",
        source = Source(id = "history-hub", name = "History Hub"),
        title = "Exploring Ancient Civilizations",
        url = "https://example.com/articles/8",
        urlToImage = "https://example.com/images/article8.jpg"
    ),
    Article(
        author = "Olivia White",
        content = "The latest research on climate change and its impact.",
        description = "How climate change is affecting our planet in 2025.",
        publishedAt = "2025-01-03T16:30:00Z",
        source = Source(id = "eco-watch", name = "Eco Watch"),
        title = "Climate Change in 2025",
        url = "https://example.com/articles/9",
        urlToImage = "https://example.com/images/article9.jpg"
    ),
    Article(
        author = "James Clark",
        content = "Breaking news about a significant political event.",
        description = "Stay informed about the latest developments in politics.",
        publishedAt = "2025-01-02T08:00:00Z",
        source = Source(id = "politics-daily", name = "Politics Daily"),
        title = "Breaking News: Political Event",
        url = "https://example.com/articles/10",
        urlToImage = "https://example.com/images/article10.jpg"
    )
)