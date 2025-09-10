// QuoteFlowApp.kt
package com.rahul.auric.quoteflow

import android.app.Application
import com.rahul.auric.quoteflow.data.db.QuoteDatabase
import com.rahul.auric.quoteflow.repositories.OfflineQuoteRepository
import com.rahul.auric.quoteflow.repositories.QuoteRepository

class QuoteFlowApp : Application() {
    // A lazy-initialized database instance. It won't be created until it's first needed.
    private val database by lazy { QuoteDatabase.getDatabase(this) }

    // A lazy-initialized repository instance.
    val repository by lazy { OfflineQuoteRepository(database.quoteDao()) }
}