package com.example.handleapp.repository.localdatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shoppingList")
data class ShoppingListItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "item")
    val item: String,

    @ColumnInfo(name = "quantity")
    val quantity: Int = 1
)

