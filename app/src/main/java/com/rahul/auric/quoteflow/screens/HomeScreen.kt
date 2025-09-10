// HomeScreen.kt
package com.rahul.auric.quoteflow.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.rahul.auric.quoteflow.composables.AddQuoteDialog
import com.rahul.auric.quoteflow.composables.QuoteCard
import com.rahul.auric.quoteflow.ui.theme.*
import com.rahul.auric.quoteflow.utils.shareQuote
import com.rahul.auric.quoteflow.viewmodels.QuoteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: QuoteViewModel,
    onNavigateToFavorites: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val quoteOfTheDay = uiState.quoteOfTheDay
    val context = LocalContext.current
    val isHomeSelected = true

    // State to manage the visibility of the dialog
    var showAddQuoteDialog by remember { mutableStateOf(false) }

    val isCurrentQuoteFavorite = quoteOfTheDay?.isFavorite ?: false

    // Conditionally show the dialog based on the state
    if (showAddQuoteDialog) {
        AddQuoteDialog(
            onDismiss = { showAddQuoteDialog = false },
            onSave = { quoteText, author ->
                viewModel.addUserQuote(quoteText, author)
                showAddQuoteDialog = false // Hide dialog after saving
                Toast.makeText(context, "Quote added!", Toast.LENGTH_SHORT).show()
            }
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Quote of the Day", style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.background),
                actions = {
                    IconButton(onClick = { viewModel.getRandomQuote() }) {
                        Icon(imageVector = Icons.Default.Refresh, contentDescription = "New Quote")
                    }
                    IconButton(onClick = onNavigateToFavorites) {
                        Icon(
                            imageVector = if (isCurrentQuoteFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                            contentDescription = "Favorites",
                            tint = if (isCurrentQuoteFavorite) FavoriteRed else MaterialTheme.colorScheme.onBackground
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                // The FAB's onClick now shows the dialog
                onClick = { showAddQuoteDialog = true },
                containerColor = AccentTeal,
                contentColor = MaterialTheme.colorScheme.background,
                shape = MaterialTheme.shapes.large
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add a new quote")
            }
        },
        bottomBar = {
            BottomAppBar(containerColor = MaterialTheme.colorScheme.surface) {
                NavigationBarItem(
                    selected = isHomeSelected, onClick = { /* No action */ },
                    icon = { Icon(imageVector = if (isHomeSelected) Icons.Filled.Home else Icons.Outlined.Home, contentDescription = "Home") },
                    label = { Text("Home") },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = BottomNavSelected, selectedTextColor = BottomNavSelected,
                        unselectedIconColor = BottomNavUnselected, unselectedTextColor = BottomNavUnselected,
                        indicatorColor = MaterialTheme.colorScheme.surface
                    )
                )
                NavigationBarItem(
                    selected = !isHomeSelected, onClick = onNavigateToFavorites,
                    icon = { Icon(imageVector = if (!isHomeSelected) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder, contentDescription = "Favorites") },
                    label = { Text("Favorites") },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = BottomNavSelected, selectedTextColor = BottomNavSelected,
                        unselectedIconColor = BottomNavUnselected, unselectedTextColor = BottomNavUnselected,
                        indicatorColor = MaterialTheme.colorScheme.surface
                    )
                )
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier.fillMaxSize().padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            if (quoteOfTheDay == null) {
                CircularProgressIndicator()
            } else {
                Column(
                    modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    QuoteCard(quote = quoteOfTheDay)
                    Spacer(modifier = Modifier.height(40.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Button(
                            onClick = {
                                viewModel.toggleFavorite()
                                val message = if (isCurrentQuoteFavorite) "Removed from Favorites" else "Added to Favorites!"
                                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                            },
                            modifier = Modifier.weight(1f).height(56.dp),
                            shape = MaterialTheme.shapes.small,
                            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                        ) {
                            Text(
                                text = if (isCurrentQuoteFavorite) "Remove from favorites" else "Save to favorites",
                                color = MaterialTheme.colorScheme.onPrimary
                            )
                        }
                        OutlinedButton(
                            onClick = { shareQuote(context, quoteOfTheDay) },
                            modifier = Modifier.weight(1f).height(56.dp),
                            shape = MaterialTheme.shapes.small
                        ) { Text("Share quote") }
                    }
                }
            }
        }
    }
}