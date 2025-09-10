// Screen.kt
package com.rahul.auric.quoteflow.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home_screen")
    object Favorites : Screen("favorites_screen")
}