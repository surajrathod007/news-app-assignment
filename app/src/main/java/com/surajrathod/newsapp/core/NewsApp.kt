package com.surajrathod.newsapp.core

import android.app.Application
import com.surajrathod.newsapp.utils.CoilImageLoader
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class NewsApp : Application() {
    override fun onCreate() {
        super.onCreate()
        CoilImageLoader.init(this)
    }
}