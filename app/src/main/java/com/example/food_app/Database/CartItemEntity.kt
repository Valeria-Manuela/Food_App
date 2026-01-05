package com.example.food_app.Database



import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_items")
data class CartItemEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    val price: Double,
    val image: String,
    val quantity: Int,
    val imagePath: String,
    val description: String,
    val calorie: Int,
    val star: Double,
    val bestFood: Boolean,
    val categoryId: String,
    val priceId: Int,
    val timeId: Int,
    val timeValue: Int,
    val locationId: Int
)
