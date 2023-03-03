package com.example.handleapp.repository.localdatabase

import kotlinx.coroutines.flow.Flow

interface ShoppingRepository {

    fun getAllItemsStream(): Flow<List<ShoppingListItem>>

    suspend fun insertShoppingItem(item: ShoppingListItem)

    suspend fun deleteShoppingList()

    suspend fun deleteItem(item: ShoppingListItem)
}