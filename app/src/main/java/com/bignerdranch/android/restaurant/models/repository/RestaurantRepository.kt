package com.bignerdranch.android.restaurant.models.repository

import com.bignerdranch.android.restaurant.models.data.*
import com.bignerdranch.android.restaurant.models.db.dao.RestaurantDao
import javax.inject.Inject

class RestaurantRepository @Inject constructor(
    private val dao: RestaurantDao
) {
    suspend fun insertRestaurants(restaurants: List<Restaurants>) =
        dao.insertRestaurants(restaurants)

    suspend fun insertLatLngs(latLng: List<LatLng>) = dao.insertLatLngs(latLng)

    suspend fun insertTimings(timings: List<Timings>) = dao.insertTimings(timings)

    suspend fun insertReviews(reviews: List<Reviews>) = dao.insertReviews(reviews)

    suspend fun insertMenus(menu: List<Menu>) = dao.insertMenus(menu)

    suspend fun insertCategories(categories: List<Categories>) = dao.insertCategories(categories)

    suspend fun insertMenuItems(menuItems: List<MenuItems>) = dao.insertMenuItems(menuItems)

    suspend fun getRestaurabtCount() = dao.getRestaurabtCount()

    suspend fun getRestaurantDetail(id: Int) = dao.getRestaurantDetail(id)

    suspend fun getMenuWithCategories() = dao.getMenuWithCategories()

    suspend fun getCategoriesWithMenuItem(id: Int) = dao.getCategoriesWithMenuItem(id)

    suspend fun getRestaurantMenuDetail(id: Int) = dao.getRestaurantMenuDetail(id)

    suspend fun getRestaurantMenuDetails() = dao.getRestaurantMenuDetails()

    suspend fun getRestaurantMenuDetailsByQuery(searchQuery: String) =
        dao.getRestaurantMenuDetailsByQuery(searchQuery)

    fun getcurrentRestaurantCount() = dao.getcurrentRestaurantCount()
}
