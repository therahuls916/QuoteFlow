// QuoteRepository.kt
package com.rahul.auric.quoteflow.repositories

import com.rahul.auric.quoteflow.data.Quote
import com.rahul.auric.quoteflow.data.db.QuoteDao
import kotlinx.coroutines.flow.Flow

// An interface that defines the contract for our repository.
// This is useful for testing and dependency injection.
interface QuoteRepository {
    fun getAllQuotes(): Flow<List<Quote>>
    fun getFavoriteQuotes(): Flow<List<Quote>>
    suspend fun updateQuote(quote: Quote)
}

// The implementation of our repository that uses the QuoteDao as its data source.
class OfflineQuoteRepository(private val quoteDao: QuoteDao) : QuoteRepository {

    // Simply pass through the request to get all quotes from the DAO.
    override fun getAllQuotes(): Flow<List<Quote>> {
        return quoteDao.getAllQuotes()
    }

    // Pass through the request to get favorite quotes from the DAO.
    override fun getFavoriteQuotes(): Flow<List<Quote>> {
        return quoteDao.getFavoriteQuotes()
    }

    // Pass through the request to update/insert a quote in the DAO.
    override suspend fun updateQuote(quote: Quote) {
        quoteDao.upsertQuote(quote)
    }
}