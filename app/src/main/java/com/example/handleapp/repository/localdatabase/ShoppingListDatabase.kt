package com.example.handleapp.repository.localdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ShoppingListItem::class], version = 1)
abstract class ShoppingListDatabase : RoomDatabase() {

    abstract fun shoppingDao(): ShoppingDao

    companion object{
        @Volatile
        private var INSTANCE: ShoppingListDatabase? = null

        fun getDatabase(
            context: Context
        ) : ShoppingListDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ShoppingListDatabase::class.java,
                    "shopping_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}