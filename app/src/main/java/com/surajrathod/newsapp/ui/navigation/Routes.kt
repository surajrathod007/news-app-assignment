package com.surajrathod.newsapp.ui.navigation

import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.currentBackStackEntryAsState
import com.surajrathod.newsapp.data.Article
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
data class ArticleDetailsRoute(val article: Article)

val ArticleDetailsRouteNavType = object : NavType<Article>(isNullableAllowed = true) {
    override fun get(bundle: Bundle, key: String): Article? =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            bundle.getParcelable(key, Article::class.java)
        } else {
            @Suppress("DEPRECATION") // for backwards compatibility
            bundle.getParcelable(key)
        }


    override fun put(bundle: Bundle, key: String, value: Article) =
        bundle.putParcelable(key, value)

    override fun parseValue(value: String): Article = Json.decodeFromString(value)

    override fun serializeAsValue(value: Article): String = Uri.encode(Json.encodeToString(value))

    override val name: String = "ArticleDetailsRoute"

}



@Composable
fun getCurrentRoute(navController: NavController): String? {

    return runCatching {
        navController.currentBackStackEntryAsState().value?.destination?.route
    }.getOrElse {
        ""
    }
}



