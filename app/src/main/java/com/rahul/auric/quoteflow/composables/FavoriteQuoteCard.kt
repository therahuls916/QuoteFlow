// FavoriteQuoteCard.kt
package com.rahul.auric.quoteflow.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.rahul.auric.quoteflow.data.Quote
import com.rahul.auric.quoteflow.ui.theme.FavoriteRed

@Composable
fun FavoriteQuoteCard(
    quote: Quote,
    onShare: () -> Unit,
    onUnfavorite: () -> Unit // Changed from onDelete
) {
    Card(
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(modifier = Modifier.padding(24.dp).fillMaxWidth()) {
            Text(
                text = "\"${quote.text}\"",
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "- ${quote.author}",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.End,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(onClick = onShare) {
                    Icon(imageVector = Icons.Default.Share, contentDescription = "Share Quote")
                }
                // The icon is now a filled heart to represent "unfavorite"
                IconButton(onClick = onUnfavorite) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = "Unfavorite Quote",
                        tint = FavoriteRed
                    )
                }
            }
        }
    }
}