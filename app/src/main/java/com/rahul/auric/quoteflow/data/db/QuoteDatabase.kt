// QuoteDatabase.kt
package com.rahul.auric.quoteflow.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rahul.auric.quoteflow.data.Quote

// The @Database annotation marks this class as a Room Database.
// - entities: An array of all the entity classes. For now, it's just Quote.
// - version: The version of the database schema. If you ever change the table
//   structure (e.g., add a column), you must increment this number.
// - exportSchema: It's good practice to set this to false unless you want to
//   export your database schema for version control.
@Database(entities = [Quote::class], version = 1, exportSchema = false)
abstract class QuoteDatabase : RoomDatabase() {

    // An abstract function that returns our QuoteDao. Room will generate the implementation.
    abstract fun quoteDao(): QuoteDao

    // The companion object allows us to access the methods for creating or getting
    // the database without having to instantiate the class.
    companion object {
        // @Volatile annotation ensures that the value of INSTANCE is always up-to-date
        // and visible to all execution threads.
        @Volatile
        private var INSTANCE: QuoteDatabase? = null

        // This function is the single entry point to get the database instance.
        fun getDatabase(context: Context): QuoteDatabase {
            // The synchronized block means that only one thread can execute this code
            // at a time, preventing multiple instances of the database from being created.
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    QuoteDatabase::class.java,
                    "quote_database" // The name of the actual database file on the device.
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}