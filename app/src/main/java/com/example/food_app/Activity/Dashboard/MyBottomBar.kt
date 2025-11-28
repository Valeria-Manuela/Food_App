package com.example.food_app.Activity.Dashboard

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.food_app.R

@Composable
@Preview
fun MyBottomBar() {

    val items = prepareBottomMenu()
    var selectedItem by remember { mutableStateOf("Home") }

    NavigationBar {

        items.forEach { item ->

            NavigationBarItem(
                selected = selectedItem == item.label,
                onClick = { selectedItem = item.label },
                icon = {
                    Icon(
                        painter = item.icon,
                        contentDescription = item.label,
                        modifier = Modifier.size(22.dp)
                    )
                }
            )
        }
    }
}

data class BottomMenuItem(
    val label: String,
    val icon: Painter
)

@Composable
fun prepareBottomMenu(): List<BottomMenuItem> {
    return listOf(
        BottomMenuItem("Home", painterResource(id = R.drawable.btn_1)),
        BottomMenuItem("Profile", painterResource(id = R.drawable.btn_5)),
        BottomMenuItem("Cart", painterResource(id = R.drawable.cart)),
        BottomMenuItem("Favorite", painterResource(id = R.drawable.btn_3)),
        BottomMenuItem("Order", painterResource(id = R.drawable.btn_4))
    )
}
