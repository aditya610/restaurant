package com.bignerdranch.android.restaurant.controllers.ui.restaurant

sealed class RestaurentRecyclerViewItem {

    class Restaurent(
        val id: Int,
        val RestaurantName: String,
        val address: String,
        val cuisineType: String
    ) : RestaurentRecyclerViewItem()

    class Categories(
        val categoryName: String
    ) : RestaurentRecyclerViewItem()

    class MenuItem(
        val menuItemName: String
    ) : RestaurentRecyclerViewItem()
}