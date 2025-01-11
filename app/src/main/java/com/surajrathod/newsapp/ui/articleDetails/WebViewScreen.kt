package com.surajrathod.newsapp.ui.articleDetails

import android.graphics.Bitmap
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.background
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
                        override fun onPageStarted(view: WebView, url: String?, favicon: Bitmap?) {
                            super.onPageStarted(view, url, favicon)
                            //isLoading.value = true // Page started loading
                        }

                        override fun onPageFinished(view: WebView, url: String?) {
                            super.onPageFinished(view, url)
                            /*// Save HTML content once the page is fully loaded
                            view.evaluateJavascript(
                                "(function() { return document.documentElement.outerHTML; })();"
                            ) { htmlContent ->
                                val file = File(context.filesDir, article.title?:"")
                                file.writeText(htmlContent ?: "")
                            }*/
                            isLoading.value = false // Page loading finished
                        }


                    }
                }
            },
            update = { webView ->
                webView.loadUrl(article.url?:"")
                /*val file = File(webView.context.filesDir, article.title?:"")
                if (file.exists()) {
                    val htmlContent = file.readText()
                    webView.loadDataWithBaseURL(null, htmlContent, "text/html", "UTF-8", null)
                } else {
                    webView.loadUrl(article.url?:"")
                }*/
            }
        )

        if (isLoading.value) {
            FullScreenLoader()
        }
    }

}