package com.example.food_app.Activity.FoodDetail

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.food_app.Domain.FoodModel
import com.example.food_app.Helper.ManagmentCart

class FoodDetailsActivity : ComponentActivity() {

    private lateinit var item: FoodModel
    private lateinit var managmentCart: ManagmentCart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val receivedItem = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("object", FoodModel::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<FoodModel>("object")
        }

        if (receivedItem == null) {
            finish()
            return
        }

        item = receivedItem
        item.numberInCart = 1

        managmentCart = ManagmentCart(this)

        setContent {
            DetailScreen(
                item = item,
                onBackClick = { finish() },
                onAddToCartClick = { managmentCart.insertItem(item) }
            )
        }
    }
}

