package com.bignerdranch.android.restaurant.controllers.ui.restaurantdetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bignerdranch.android.restaurant.controllers.ui.restaurant.RestaurentRecyclerViewItem
import com.bignerdranch.android.restaurant.models.data.*
import com.bignerdranch.android.restaurant.models.repository.RestaurantRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RestaurantDetailViewModel @Inject constructor(
    private val repository: RestaurantRepository
) : ViewModel() {
    var id: Int = 0
        set(value) {
            field = value
            loadIntialData(value)
        }

    val name = MutableStateFlow("")
    val address = MutableStateFlow("")
    val cuisine_type = MutableStateFlow("")

    private var timings = listOf<Timings>()
    private var reviews = listOf<Reviews>()
    private var categoryWithMenuItemslist = mutableListOf<CategoriesWithMenuItems>()
    private var _menulist = MutableStateFlow<List<CategoriesWithMenuItems>>(emptyList())
    val menuList = _menulist


    var adapterList = mutableListOf<RestaurentDetailRecyclerViewItem>()
    val adapterData = MutableLiveData(mutableListOf<RestaurentDetailRecyclerViewItem>())

    fun loadIntialData(id: Int) {
        viewModelScope.launch {
            if (id != 0) {
                val detail = repository.getRestaurantDetail(id)
                if (detail != null) {
                    name.value = detail.name
                    address.value = detail.address
                    cuisine_type.value = detail.cuisine_type
                    timings = detail.timings
                    reviews = detail.reviews
                    getMenu()
                }
            }
        }
    }

    fun getMenu() {
        viewModelScope.launch {
            val restaurantMenuDetail = repository.getRestaurantMenuDetail(id)
            val categoriesList = restaurantMenuDetail.categories
            for (categories in categoriesList) {
                val categoryWithMenuItems = repository.getCategoriesWithMenuItem(categories.id)
                categoryWithMenuItemslist.add(categoryWithMenuItems)
            }
            _menulist.value = categoryWithMenuItemslist
        }
    }

    fun setAdapter() {
        adapterList.clear()
        for (categoryWithMenuItems in categoryWithMenuItemslist) {
            adapterList.add(RestaurentDetailRecyclerViewItem.Categories(categoryWithMenuItems.category.name))
            val menuItemsList = categoryWithMenuItems.menuList
            for (menuItems in menuItemsList) {
                adapterList.add(RestaurentDetailRecyclerViewItem.MenuItems(menuItems))
            }
        }
        var flag = 0;
        for (timing in timings) {
            flag++;
            if (flag == 1) {
                adapterList.add(RestaurentDetailRecyclerViewItem.Categories(SeedData.Timing))
            }
            adapterList.add(RestaurentDetailRecyclerViewItem.Timings(timing))
        }
        flag = 0;
        for (review in reviews) {
            flag++;
            if (flag == 1) {
                adapterList.add(RestaurentDetailRecyclerViewItem.Categories(SeedData.Review))
            }
            adapterList.add(RestaurentDetailRecyclerViewItem.Reviews(review))
        }

        adapterData.value = adapterList

    }
}