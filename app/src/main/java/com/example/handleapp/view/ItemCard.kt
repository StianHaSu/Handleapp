package com.example.handleapp.view

import android.app.LocaleConfig
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.handleapp.repository.localdatabase.ShoppingListItem
import com.example.handleapp.viewmodel.ShoppingViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemCard(
    item: ShoppingListItem,
    viewModel: ShoppingViewModel
) {
    ElevatedCard() {
        val config = LocalConfiguration.current
        val height = config.screenHeightDp
        val width = config.screenWidthDp

        Column(
            modifier = Modifier
                .width((width / 2).dp)
                .height(100.dp)
                .padding(top = 5.dp, end = 5.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = {viewModel.deleteItem(item)},
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.error),
                modifier = Modifier
                    .size(25.dp)
                    .align(Alignment.End),
                contentPadding = PaddingValues(0.dp)
            ) {
                Text(text = "X")
            }
            Text(
                text = item.item,
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium,

            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = item.quantity.toString(),
                fontWeight = FontWeight.SemiBold
            )

        }

    }


}