package com.bignerdranch.android.restaurant.models.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.bignerdranch.android.restaurant.models.data.*
import kotlinx.coroutines.flow.Flow

@Dao
interface RestaurantDao {
    @Insert
    suspend fun insertRestaurants(restaurants: List<Restaurants>)

    @Insert
    suspend fun insertLatLngs(latLng: List<LatLng>)

    @Insert
    suspend fun insertTimings(timings: List<Timings>)

    @Insert
    suspend fun insertReviews(reviews: List<Reviews>)

    @Insert
    suspend fun insertMenus(menu: List<Menu>)

    @Insert
    suspend fun insertCategories(categories: List<Categories>)

    @Insert
    suspend fun insertMenuItems(menuItems: List<MenuItems>)

    @Query("Select Count(*) from Restaurants")
    suspend fun getRestaurabtCount(): Int

    @Query("Select Count(*) from Restaurants")
    fun getcurrentRestaurantCount(): Flow<Int>

    @Transaction
    @Query("Select * from RestaurantDetails where id = :id")
    suspend fun getRestaurantDetail(id: Int): RestaurantDetails

    @Transaction
    @Query("Select * from Menu")
    suspend fun getMenuWithCategories(): List<MenuWithCategories>

    @Transaction
    @Query("Select * from Categories where id = :id")
    suspend fun getCategoriesWithMenuItem(id: Int): CategoriesWithMenuItems

    @Transaction
    @Query("Select * from RestaurantMenuDetails")
    suspend fun getRestaurantMenuDetails(): List<RestaurantMenuDetails>

    @Transaction
    @Query("Select * from RestaurantMenuDetails where id = :id")
    suspend fun getRestaurantMenuDetail(id: Int): RestaurantMenuDetails

    @Transaction
    @Query("Select * from RestaurantMenuDetails Where address Like :query or cuisine_type Like :query")
    suspend fun getRestaurantMenuDetailsByQuery(query: String): List<RestaurantMenuDetails>

}