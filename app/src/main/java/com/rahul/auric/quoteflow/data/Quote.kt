// Quote.kt
package com.rahul.auric.quoteflow.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// @Entity annotation marks this class as a database table.
@Entity(tableName = "quotes")
data class Quote(
    // @PrimaryKey marks this field as the unique identifier for each row.
    // autoGenerate = true tells Room to automatically generate an ID for new quotes.
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0, // Default to 0, Room will handle the rest.

    // @ColumnInfo specifies the name of the column in the table.
    @ColumnInfo(name = "quote_text")
    val text: String,

    @ColumnInfo(name = "author_name")
    val author: String,

    // NEW FIELD: We will use this column to track which quotes are favorites.
    @ColumnInfo(name = "is_favorite")
    var isFavorite: Boolean = false
)