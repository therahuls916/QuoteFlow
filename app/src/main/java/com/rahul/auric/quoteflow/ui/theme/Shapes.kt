// Shapes.kt
package com.rahul.auric.quoteflow.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

val Shapes = Shapes(
    // Used for Cards (Quote Card, Favorite Quote Items)
    medium = RoundedCornerShape(20.dp),

    // Used for Buttons and Chips
    small = RoundedCornerShape(16.dp),

    // Used for Dialogs and BottomSheets
    large = RoundedCornerShape(24.dp)
)