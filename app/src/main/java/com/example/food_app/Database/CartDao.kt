package com.example.food_app.Database

import androidx.room.*

@Dao
interface CartDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: CartItemEntity)

    @Query("SELECT * FROM cart_items")
    suspend fun getAllItems(): List<CartItemEntity>

    @Query("DELETE FROM cart_items WHERE id = :itemId")
    suspend fun removeItem(itemId: Int)

    @Query("DELETE FROM cart_items")
    suspend fun clearCart()
}
