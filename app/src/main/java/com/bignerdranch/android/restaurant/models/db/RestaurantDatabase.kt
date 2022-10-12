package com.bignerdranch.android.restaurant.models.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bignerdranch.android.restaurant.models.data.*
import com.bignerdranch.android.restaurant.models.db.dao.RestaurantDao

@Database(
    entities = [Restaurants::class, LatLng::class, Timings::class, Reviews::class, Menu::class, Categories::class, MenuItems::class],
    views = [RestaurantDetails::class, RestaurantMenuDetails::class],
    version = 5,
    exportSchema = false
)
abstract class RestaurantDatabase: RoomDatabase() {
    abstract fun restaurantDao(): RestaurantDao
}