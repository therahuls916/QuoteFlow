// FavoritesScreen.kt
package com.rahul.auric.quoteflow.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import com.rahul.auric.quoteflow.composables.FavoriteQuoteCard
import com.rahul.auric.quoteflow.utils.shareQuote
import com.rahul.auric.quoteflow.viewmodels.QuoteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesScreen(
    viewModel: QuoteViewModel,
    onNavigateBack: () -> Unit
) {
    // Collect the list of favorites directly from the new StateFlow
    val favoriteQuotes by viewModel.favoriteQuotes.collectAsState()
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Favorites", style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.background),
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        if (favoriteQuotes.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize().padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "You have no favorite quotes yet.", style = MaterialTheme.typography.bodyMedium)
            }
        } else {
            LazyColumn(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
                items(favoriteQuotes, key = { quote -> quote.id }) { quote ->
                    FavoriteQuoteCard(
                        quote = quote,
                        onShare = { shareQuote(context, quote) },
                        onUnfavorite = {
                            viewModel.removeFavorite(quote) // Use the new ViewModel function
                            Toast.makeText(context, "Removed from Favorites", Toast.LENGTH_SHORT).show()
                        }
                    )
                }
            }
        }
    }
}