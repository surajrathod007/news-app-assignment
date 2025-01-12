package com.surajrathod.newsapp.ui.articleDetails

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.surajrathod.newsapp.data.Article
import com.surajrathod.newsapp.ui.components.FullScreenLoader
import java.io.File

@Composable
fun WebViewScreen(article: Article) {
    val isLoading = remember { mutableStateOf(true) }
    Box(modifier = Modifier.fillMaxSize()){
        AndroidView(
            modifier = Modifier.fillMaxSize(),
            factory = { context ->
                WebView(context).apply {
                    settings.javaScriptEnabled = true
                    settings.domStorageEnabled = true
                    settings.loadWithOverviewMode = true
                    settings.useWideViewPort = true
                    settings.setSupportZoom(true)

                    webViewClient = object : WebViewClient() {

                        override fun onPageFinished(view: WebView, url: String?) {
                            super.onPageFinished(view, url)
                            isLoading.value = false
                        }


                    }
                }
            },
            update = { webView ->
                val file = File(webView.context.filesDir, article.getHtmlFileName())
                if (file.exists()) {
                    val htmlContent = file.readText()
                    webView.loadDataWithBaseURL(null, htmlContent, "text/html", "UTF-8", null)
                } else {
                    webView.loadUrl(article.url)
                }
            }
        )

        if (isLoading.value) {
            FullScreenLoader()
        }
    }

}