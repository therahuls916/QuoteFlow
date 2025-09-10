// ShareUtils.kt
package com.rahul.auric.quoteflow.utils

import android.content.Context
import android.content.Intent
import com.rahul.auric.quoteflow.data.Quote

fun shareQuote(context: Context, quote: Quote) {
    // Format the text to be shared
    val shareText = "\"${quote.text}\" - ${quote.author}"

    // Create a SEND intent
    val sendIntent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, shareText)
        type = "text/plain"
    }

    // Create a chooser to show the user a list of apps to share with
    val shareIntent = Intent.createChooser(sendIntent, "Share Quote via")

    // Start the activity
    context.startActivity(shareIntent)
}