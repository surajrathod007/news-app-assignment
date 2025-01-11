package com.surajrathod.newsapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

@Composable
fun NewsCard(
    imageUrl: String,
    headline: String,
    description: String,
    onDeleteClick: () -> Unit,
    onReadMoreClick: () -> Unit,
    showDeleteIcon: Boolean = false
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        shape = MaterialTheme.shapes.medium,
        elevation = 4.dp
    ) {
        Box {
            // Content of the card
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                // Image
                Image(
                    painter = rememberAsyncImagePainter(imageUrl),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(MaterialTheme.shapes.medium),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Headline
                Text(
                    text = headline,
                    style = MaterialTheme.typography.h6,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(4.dp))

                // Description
                Text(
                    text = description,
                    style = MaterialTheme.typography.body2,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Read More Button
                Button(modifier = Modifier.align(Alignment.End), onClick = onReadMoreClick) {
                    Text("Read More")
                }
            }

            // Delete Icon
            if (showDeleteIcon) {
                IconButton(
                    onClick = onDeleteClick,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete",
                        tint = MaterialTheme.colors.error
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun NewsCardPreview() {
    MaterialTheme {
        NewsCard(
            imageUrl = "https://via.placeholder.com/300",
            headline = "Breaking News Headline",
            description = "This is a short description of the news article. It provides a quick overview.",
            onDeleteClick = { /* Handle delete action */ },
            onReadMoreClick = { /* Handle read more action */ },
            showDeleteIcon = true // Change to false to hide the delete icon
        )
    }
}

