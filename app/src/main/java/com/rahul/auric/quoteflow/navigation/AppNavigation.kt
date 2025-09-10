// AppNavigation.kt
package com.rahul.auric.quoteflow.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rahul.auric.quoteflow.QuoteFlowApp
import com.rahul.auric.quoteflow.screens.FavoritesScreen
import com.rahul.auric.quoteflow.screens.HomeScreen
import com.rahul.auric.quoteflow.viewmodels.QuoteViewModel
import com.rahul.auric.quoteflow.viewmodels.QuoteViewModelFactory

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    // Get the application context to access the repository
    val application = LocalContext.current.applicationContext as QuoteFlowApp

    // Create the ViewModel using our custom factory
    val viewModel: QuoteViewModel = viewModel(
        factory = QuoteViewModelFactory(application.repository)
    )

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(route = Screen.Home.route) {
            HomeScreen(
                viewModel = viewModel,
                onNavigateToFavorites = {
                    navController.navigate(Screen.Favorites.route)
                }
            )
        }

        composable(route = Screen.Favorites.route) {
            FavoritesScreen(
                viewModel = viewModel,
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}