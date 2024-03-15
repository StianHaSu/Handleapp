package com.example.handleapp.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalConfiguration

import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.handleapp.viewmodel.ShoppingViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingScreen(
    viewModel: ShoppingViewModel = viewModel()
){
    val shoppingState = viewModel.shoppingState.collectAsState()
    val config = LocalConfiguration.current
    val screenWidth = config.screenWidthDp
    val focus = LocalFocusManager.current

    Scaffold(
        topBar = {SmallTopAppBar(
            title = {
                Text(text = "Shopping list")
            }, colors = TopAppBarDefaults.smallTopAppBarColors(MaterialTheme.colorScheme.inversePrimary)
        ) }

    ) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(innerPadding)
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            TextField(
                value = viewModel.newItem,
                onValueChange = { newValue -> viewModel.updateCurrentItem(newValue) },
                placeholder = { Text(text = "Item name") },
                keyboardActions = KeyboardActions(onDone = {
                    viewModel.addCurrentItem()
                    focus.clearFocus()
                }),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(10.dp))

            AddingElements(viewModel = viewModel)

            Spacer(modifier = Modifier.height(15.dp))

            ShoppingListCard(viewModel = viewModel)

            Spacer(modifier = Modifier.height(15.dp))

            Button(
                onClick = {viewModel.deleteShoppingList()},
                modifier = Modifier
                    .width((screenWidth - 30).dp)
                    .height(45.dp),
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.error),
                shape = RectangleShape
            ) {
                Text(text = "Delete shopping list")
            }
        }
    }
}

@Composable
fun AddingElements(viewModel: ShoppingViewModel){
    val focus = LocalFocusManager.current
    val config = LocalConfiguration.current
    val screenHeight = config.screenHeightDp
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height((screenHeight * 0.15).dp),
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {

        Button(
            modifier = Modifier.width(300.dp),
            colors = ButtonDefaults.buttonColors(Color(0xFF0d8f3e)),
            onClick = {
                viewModel.addCurrentItem()
                focus.clearFocus()
            }) {
            Text(
                text = "Add to shopping list",
                fontWeight = FontWeight.SemiBold
            )
        }
        
        Spacer(modifier = Modifier.height(10.dp))
        
        Row(
            horizontalArrangement = Arrangement.spacedBy(35.dp)
        ) {
            Button(
                modifier = Modifier.size(50.dp),
                onClick = { viewModel.updateCurrentQuantity(-1) },
                shape = RoundedCornerShape(100),
                contentPadding = PaddingValues(0.dp)

            ) {
                Text(
                    text = "-",
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp
                )
            }

            Text(
                text = viewModel.quantity.toString(),
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp,
                modifier = Modifier.padding(top = 7.dp)
            )

            Button(
                modifier = Modifier.size(50.dp),
                onClick = { viewModel.updateCurrentQuantity(1) },
                shape = RoundedCornerShape(100),
                contentPadding = PaddingValues(0.dp)

            ) {
                Text(
                    text = "+",
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp
                )
            }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingListCard(
    viewModel: ShoppingViewModel
) {

    val shoppingState = viewModel.shoppingState.collectAsState()
    val config = LocalConfiguration.current
    val screenWidth = config.screenWidthDp
    val screenHeight = config.screenHeightDp

    ElevatedCard(
        modifier = Modifier.width((screenWidth-20).dp),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primaryContainer)
    ) {
        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
                .height((screenHeight * 0.5).dp),
            columns = GridCells.Adaptive(minSize = 100.dp),
            verticalArrangement = Arrangement.spacedBy(3.dp),
            horizontalArrangement = Arrangement.spacedBy(3.dp)

        ) {
            items(shoppingState.value.shoppingListItems) { item ->
                ItemCard(item = item, viewModel)
            }
        }
    }
}











