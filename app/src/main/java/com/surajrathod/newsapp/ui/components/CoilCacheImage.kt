package com.surajrathod.newsapp.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import com.surajrathod.newsapp.utils.CoilImageLoader

@Composable
fun CoilCacheImage(modifier: Modifier = Modifier,imageUrl: String) {
    AsyncImage(
        modifier = modifier,
        model = imageUrl,
        imageLoader = CoilImageLoader.imageLoader,
        contentDescription = "Image",
        contentScale = ContentScale.Crop
    )
}

