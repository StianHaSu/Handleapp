package com.example.handleapp.repository.localdatabase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ShoppingDao {
    @Query("SELECT * FROM shoppingList ORDER BY quantity DESC")
    fun getShoppingList(): Flow<List<ShoppingListItem>>

    @Insert
    suspend fun insertShoppingItem(item: ShoppingListItem)

    @Query("DELETE FROM shoppingList")
    suspend fun deleteShoppingList()

    @Delete
    suspend fun deleteItem(item: ShoppingListItem)

}