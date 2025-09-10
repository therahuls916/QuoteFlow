// QuoteViewModel.kt
package com.rahul.auric.quoteflow.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rahul.auric.quoteflow.data.Quote
import com.rahul.auric.quoteflow.repositories.QuoteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

// This is the initial data that will be used to populate the database
// ONLY on the very first launch of the app.
private val initialQuotes = listOf(
    Quote(1, "Be yourself; everyone else is already taken.", "Oscar Wilde"),
    Quote(2, "Two things are infinite: the universe and human stupidity; and I'm not sure about the universe.", "Albert Einstein"),
    Quote(3, "In the end, we will remember not the words of our enemies, but the silence of our friends.", "Martin Luther King Jr."),
    Quote(4, "Success is not final, failure is not fatal: It is the courage to continue that counts.", "Winston Churchill"),
    Quote(5, "You miss 100% of the shots you don't take.", "Wayne Gretzky"),
    Quote(6, "The only way to do great work is to love what you do.", "Steve Jobs")
)

data class QuoteUiState(
    val quoteOfTheDay: Quote? = null
)

class QuoteViewModel(private val repository: QuoteRepository) : ViewModel() {

    private val _quoteOfTheDay = MutableStateFlow<Quote?>(null)

    val uiState: StateFlow<QuoteUiState> = _quoteOfTheDay
        .combine(repository.getFavoriteQuotes()) { quote, favorites ->
            QuoteUiState(quoteOfTheDay = quote)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = QuoteUiState()
        )

    val favoriteQuotes: StateFlow<List<Quote>> = repository.getFavoriteQuotes()
        .stateIn(
            scope = viewModelScope,
            // THIS IS THE CORRECTED LINE
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    init {
        viewModelScope.launch {
            val allQuotes = repository.getAllQuotes().first()
            if (allQuotes.isEmpty()) {
                // Now uses the private 'initialQuotes' list
                initialQuotes.forEach { repository.updateQuote(it.copy(id = 0)) }
            }
            getRandomQuote()
        }
    }

    fun getRandomQuote() {
        viewModelScope.launch {
            val allQuotes = repository.getAllQuotes().first()
            if (allQuotes.isNotEmpty()) {
                _quoteOfTheDay.value = allQuotes.random()
            }
        }
    }

    fun toggleFavorite() {
        viewModelScope.launch {
            _quoteOfTheDay.value?.let { currentQuote ->
                val updatedQuote = currentQuote.copy(isFavorite = !currentQuote.isFavorite)
                repository.updateQuote(updatedQuote)
                _quoteOfTheDay.value = updatedQuote
            }
        }
    }

    fun removeFavorite(quote: Quote) {
        viewModelScope.launch {
            val updatedQuote = quote.copy(isFavorite = false)
            repository.updateQuote(updatedQuote)
        }
    }

    fun addUserQuote(quoteText: String, author: String) {
        viewModelScope.launch {
            val newQuote = Quote(
                id = 0, // Set to 0 to let Room auto-generate the ID
                text = quoteText,
                author = author.ifBlank { "Anonymous" }, // Use "Anonymous" if author is empty
                isFavorite = false // New quotes are not favorites by default
            )
            repository.updateQuote(newQuote)
        }
    }
}