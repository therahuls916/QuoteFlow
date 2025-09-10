// MainActivity.kt
package com.rahul.auric.quoteflow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.rahul.auric.quoteflow.navigation.AppNavigation
import com.rahul.auric.quoteflow.ui.theme.QuoteFlowTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QuoteFlowTheme {
                // We now call our AppNavigation composable, which handles everything.
                AppNavigation()
            }
        }
    }
}