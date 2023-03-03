package com.example.handleapp.viewmodel

import android.app.Application
import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.util.copy
import com.example.handleapp.repository.localdatabase.AppDataContainer
import com.example.handleapp.repository.localdatabase.ShoppingListItem
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ShoppingViewModel(context: Context): ViewModel() {
    private val localDatabase = AppDataContainer(context).shoppingRepository
    private var _shoppingState = MutableStateFlow(ShoppingUiState())
    val shoppingState = _shoppingState.asStateFlow()

    init {
        getAllItems()
    }

    var newItem by mutableStateOf("")
        private set
    var quantity by mutableStateOf(1)
        private set

    fun updateCurrentItem(item: String){
        newItem = item
    }

    fun addCurrentItem() {
        if (newItem.isBlank())return;
        viewModelScope.launch {
            val newShoppingItem = ShoppingListItem( item =  newItem, quantity = quantity)
            localDatabase.insertShoppingItem(newShoppingItem)
            newItem = ""
            quantity = 1


            _shoppingState.update { ShoppingUiState(localDatabase.getAllItemsStream().first()) }
        }
    }

    fun getAllItems() {
        viewModelScope.launch {
            _shoppingState.update {
                ShoppingUiState(localDatabase.getAllItemsStream().first())
            }
        }

    }

    fun deleteShoppingList() {
        viewModelScope.launch {
            localDatabase.deleteShoppingList()
            _shoppingState.update {
                ShoppingUiState()
            }
        }
    }

    fun deleteItem(item: ShoppingListItem){
        viewModelScope.launch {
            localDatabase.deleteItem(item)
            getAllItems()
        }
    }

    fun updateCurrentQuantity(quantity: Int) {
        this.quantity += quantity
        if (quantity < 1) this.quantity = 1

    }
}