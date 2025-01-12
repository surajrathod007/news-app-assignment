package com.surajrathod.newsapp.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun FullScreenError(errorText: String = "",errorButtonText: String? = null,onErrorButtonClick: ()-> Unit = {}){
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = errorText, style = MaterialTheme.typography.bodyMedium)
            if(!errorButtonText.isNullOrEmpty()){
                Spacer(modifier = Modifier.height(6.dp))
                Button(onClick = { onErrorButtonClick.invoke() }) {
                    Text(text = errorButtonText)
                }
            }
        }

    }
}