package com.example.handleapp.repository.localdatabase

import android.content.Context

interface AppContainer {
    val shoppingRepository: ShoppingRepository
}

class AppDataContainer(private val context: Context) : AppContainer {
    override val shoppingRepository: ShoppingRepository by lazy {
        OfflineShoppingRepository(ShoppingListDatabase.getDatabase(context).shoppingDao())
    }
}