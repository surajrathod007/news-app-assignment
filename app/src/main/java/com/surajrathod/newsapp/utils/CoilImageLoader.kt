package com.surajrathod.newsapp.utils

import android.content.Context
import coil.ImageLoader
import coil.disk.DiskCache

object CoilImageLoader {
    lateinit var imageLoader: ImageLoader

    fun init(context: Context) {
        imageLoader = ImageLoader.Builder(context)
            .diskCache(
                DiskCache.Builder()
                    .directory(context.cacheDir.resolve("image_cache"))
                    .maxSizeBytes(1024L * 1024L * 100L)
                    .build()
            )
            .build()
    }
}