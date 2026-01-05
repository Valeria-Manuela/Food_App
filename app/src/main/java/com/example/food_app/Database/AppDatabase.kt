package com.example.food_app.Database

import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase


@Database(
    entities = [CartItemEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cartDao(): CartDao
}
