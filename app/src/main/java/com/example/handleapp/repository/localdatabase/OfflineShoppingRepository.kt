package com.example.handleapp.repository.localdatabase

import kotlinx.coroutines.flow.Flow

class OfflineShoppingRepository (private val shoppingDao: ShoppingDao): ShoppingRepository{
    override fun getAllItemsStream(): Flow<List<ShoppingListItem>> = shoppingDao.getShoppingList()

    override suspend fun insertShoppingItem(item: ShoppingListItem) = shoppingDao.insertShoppingItem(item)

    override suspend fun deleteShoppingList() = shoppingDao.deleteShoppingList()

    override suspend fun deleteItem(item: ShoppingListItem) = shoppingDao.deleteItem(item)

}