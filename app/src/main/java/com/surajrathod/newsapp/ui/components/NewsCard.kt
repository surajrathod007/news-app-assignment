package com.surajrathod.newsapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.Coil
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
            .padding(horizontal = 12.dp),
        shape = MaterialTheme.shapes.small,
    ) {
        Box {

            Column(
                modifier = Modifier.padding(16.dp)
            ) {

                Row(modifier = Modifier.fillMaxWidth()) {
                    CoilCacheImage(
                        imageUrl = imageUrl,
                        modifier = Modifier
                            .size(80.dp)
                            .clip(MaterialTheme.shapes.small),
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Column(
                        modifier = Modifier
                            .weight(1f)

                    ) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = headline,
                            style = MaterialTheme.typography.bodyLarge,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )

                        Spacer(modifier = Modifier.height(4.dp))


                        Text(
                            text = description,
                            style = MaterialTheme.typography.bodySmall,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )

                    }
                    // Delete Icon
                    if (showDeleteIcon) {
                        Icon(
                            modifier = Modifier.clickable {
                                onDeleteClick.invoke()
                            },
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Delete",
                            tint = MaterialTheme.colorScheme.tertiary
                        )
                    }
                }

                Text(
                    text = "Read more...", modifier = Modifier
                        .align(Alignment.End)
                        .clickable {
                            onReadMoreClick.invoke()
                        }, style = MaterialTheme.typography.bodyMedium
                )

            }


        }
    }
}

@Preview
@Composable
fun NewsCardPreview() {
    MaterialTheme {
        NewsCard(
            imageUrl = "",
            headline = "Breaking News Headline",
            description = "This is a short description of the news article. It provides a quick overview.",
            onDeleteClick = { },
            onReadMoreClick = { },
            showDeleteIcon = true
        )
    }
}

