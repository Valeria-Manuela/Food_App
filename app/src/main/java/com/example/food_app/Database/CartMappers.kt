package com.example.food_app.Database



import com.example.food_app.Domain.FoodModel

fun FoodModel.toCartItemEntity(): CartItemEntity {
    return CartItemEntity(
        id = Id,
        title = Title,
        price = Price,
        quantity = numberInCart,
        imagePath = ImagePath,
        description = Description,
        calorie = Calorie,
        star = Star,
        bestFood = BestFood,
        categoryId = CategoryId,
        priceId = PriceId,
        timeId = TimeId,
        timeValue = TimeValue,
        image = ImagePath,
        locationId = LocationId
    )
}
