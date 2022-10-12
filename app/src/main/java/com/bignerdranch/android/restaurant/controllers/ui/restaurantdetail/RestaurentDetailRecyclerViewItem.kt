package com.bignerdranch.android.restaurant.controllers.ui.restaurantdetail

sealed class RestaurentDetailRecyclerViewItem {

    class Reviews(
        val reviews:com.bignerdranch.android.restaurant.models.data.Reviews
    ):RestaurentDetailRecyclerViewItem()

    class Timings(
        val timings: com.bignerdranch.android.restaurant.models.data.Timings
    ):RestaurentDetailRecyclerViewItem()

    class MenuItems(
        val menu: com.bignerdranch.android.restaurant.models.data.MenuItems
    ):RestaurentDetailRecyclerViewItem()

    class Categories(
        val categories: String
        ):RestaurentDetailRecyclerViewItem()

}