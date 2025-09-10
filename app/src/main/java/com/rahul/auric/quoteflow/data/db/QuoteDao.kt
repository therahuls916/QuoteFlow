// QuoteDao.kt
package com.rahul.auric.quoteflow.data.db

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.rahul.auric.quoteflow.data.Quote
import kotlinx.coroutines.flow.Flow

// The @Dao annotation identifies this interface as a Data Access Object for Room.
@Dao
interface QuoteDao {

    // The @Upsert annotation is a convenient way to insert a new entity
    // or update an existing one if it already exists (based on its Primary Key).
    // We'll use this to both add new quotes and to update a quote's favorite status.
    @Upsert
    suspend fun upsertQuote(quote: Quote)

    // The @Query annotation lets us write custom SQL queries.
    // This query selects all columns from the "quotes" table.
    // The return type is Flow<List<Quote>>, which is powerful. Room will
    // automatically emit a new list of quotes whenever the data changes.
    @Query("SELECT * FROM quotes")
    fun getAllQuotes(): Flow<List<Quote>>

    // This query is similar, but it only selects quotes where the "is_favorite" column is 1 (true).
    // This will be the data source for our Favorites Screen.
    @Query("SELECT * FROM quotes WHERE is_favorite = 1")
    fun getFavoriteQuotes(): Flow<List<Quote>>
}