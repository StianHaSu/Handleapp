package com.example.handleapp.viewmodel

import androidx.compose.ui.text.input.TextFieldValue
import com.example.handleapp.repository.localdatabase.ShoppingListItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

data class ShoppingUiState(
    val shoppingListItems: List<ShoppingListItem> = listOf(),
)
