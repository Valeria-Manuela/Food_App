package com.example.food_app.Domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FoodModel(
    val Id: Int = 0,
    val ImagePath: String = "",
    val Title: String = "",
    val Description: String = "",
    val Price: Double = 0.0,
    val Calorie: Int = 0,
    val Star: Double = 0.0,
    val BestFood: Boolean = false,
    val TimeValue: Int = 0,
    val CategoryId: String = "",
    val PriceId: Int = 0,
    val TimeId: Int = 0,
    val LocationId: Int = 0,
    var numberInCart: Int = 0
) : Parcelable
