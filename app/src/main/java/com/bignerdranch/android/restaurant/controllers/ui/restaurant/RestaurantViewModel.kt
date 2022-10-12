package com.bignerdranch.android.restaurant.controllers.ui.restaurant

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Query
import com.bignerdranch.android.restaurant.models.data.CategoriesWithMenuItems
import com.bignerdranch.android.restaurant.models.data.RestaurantDetails
import com.bignerdranch.android.restaurant.models.data.RestaurentWithMenu
import com.bignerdranch.android.restaurant.models.data.SeedData
import com.bignerdranch.android.restaurant.models.repository.RestaurantRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RestaurantViewModel @Inject constructor(
    private val repository: RestaurantRepository
) : ViewModel() {

    var list = mutableSetOf<RestaurentWithMenu>()
    var adapterList = mutableListOf<RestaurentRecyclerViewItem>()
    val adapterData = MutableLiveData(mutableListOf<RestaurentRecyclerViewItem>())
    var data = MutableLiveData(listOf<RestaurentWithMenu>())
    var categoryWithMenuItemslist = mutableSetOf<CategoriesWithMenuItems>()
    val restaurentCount = repository.getcurrentRestaurantCount().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000), 0
    )

    fun setRecyclerAdapterList(list: List<RestaurentWithMenu>) {
        adapterList.clear()
        for (restaurantWithMenu in list) {
            val restaurantDetail = restaurantWithMenu.restaurentDetail
            adapterList.add(
                RestaurentRecyclerViewItem.Restaurent(
                    restaurantDetail.id,
                    restaurantDetail.name,
                    restaurantDetail.address,
                    restaurantDetail.cuisine_type
                )
            )
            val categoriesWithMenuItemsList = restaurantWithMenu.menuDetail
            for (categoriesWithMenuItems in categoriesWithMenuItemsList) {
                adapterList.add(RestaurentRecyclerViewItem.Categories(categoriesWithMenuItems.category.name))
                val menuItemsList = categoriesWithMenuItems.menuList
                for (menuItems in menuItemsList) {
                    adapterList.add(RestaurentRecyclerViewItem.MenuItem(menuItems.name))
                }
            }
        }
        adapterData.value = adapterList
    }

    fun getData() {
        viewModelScope.launch {
            val restaurantMenuDetailList = repository.getRestaurantMenuDetails().toSet()
            list.clear()
            for (restaurantMenuDetail in restaurantMenuDetailList) {
                val categoriesList = restaurantMenuDetail.categories
                categoryWithMenuItemslist.clear()
                for (categories in categoriesList) {
                    val categoryWithMenuItems = repository.getCategoriesWithMenuItem(categories.id)
                    categoryWithMenuItemslist.add(categoryWithMenuItems)
                }
                val RestaurentWithMenu =
                    RestaurentWithMenu(restaurantMenuDetail, categoryWithMenuItemslist.toList())
                list.add(RestaurentWithMenu)
            }
            data.value = list.toList()
        }
    }

    fun searchDataList(query: String) {
        val newList = mutableListOf<RestaurentWithMenu>()
        newList.clear()
        if (list != null) {
            for (restaurantWithMenu in list) {
                var flag = 0;
                if (restaurantWithMenu.restaurentDetail.address.lowercase()
                        .contains(query.lowercase()) ||
                    restaurantWithMenu.restaurentDetail.cuisine_type.lowercase()
                        .contains(query.lowercase())
                ) {
                    flag = 1;
                } else {
                    val categoriesWithMenuItemsList = restaurantWithMenu.menuDetail
                    var list = mutableListOf<String>()
                    for (categoriesWithMenuItems in categoriesWithMenuItemsList) {
                        val menuItemsList = categoriesWithMenuItems.menuList
                        for (menuItems in menuItemsList) {
                            list.add(menuItems.name.lowercase())
                        }
                    }
                    if (list.any { it.contains(query.lowercase()) }) {
                        flag = 1;
                    }
                }
                if (flag == 1) {
                    newList.add(restaurantWithMenu)
                }
            }
        }
        data.value = newList
    }


    fun intialize() {
        viewModelScope.launch(Dispatchers.IO) {
            val data = repository.getRestaurabtCount()
            if (data == null || data == 0) {
                repository.insertRestaurants(SeedData.restaurantsList)
                repository.insertLatLngs(SeedData.latLngList)
                repository.insertReviews(SeedData.reviewsList)
                repository.insertTimings(SeedData.timingsList)
                repository.insertMenus(SeedData.menuList)
                repository.insertCategories(SeedData.categories)
                repository.insertMenuItems(SeedData.menuItemsList)
            }
        }
    }

    fun searchData(p0: String) {
        val searchQuery = "%$p0%"
        viewModelScope.launch {
            list.clear()
            val restaurantMenuDetailList = repository.getRestaurantMenuDetailsByQuery(searchQuery)
            for (restaurantMenuDetail in restaurantMenuDetailList) {
                val categoriesList = restaurantMenuDetail.categories
                categoryWithMenuItemslist.clear()
                for (categories in categoriesList) {
                    val categoryWithMenuItems = repository.getCategoriesWithMenuItem(categories.id)
                    categoryWithMenuItemslist.add(categoryWithMenuItems)
                }
                val RestaurentWithMenu =
                    RestaurentWithMenu(restaurantMenuDetail, categoryWithMenuItemslist.toList())
                list.add(RestaurentWithMenu)
            }
            data.value = list.toList()
        }

    }
}