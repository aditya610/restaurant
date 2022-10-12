package com.bignerdranch.android.restaurant.models.data

import androidx.room.DatabaseView
import androidx.room.Embedded
import androidx.room.Relation

@DatabaseView(
    """
        SELECT
        R.id, R.name, R.neighborhood , R.photograph , R.address , R.cuisine_type 
        FROM Restaurants AS R
    """
)
data class RestaurantDetails(
    val id: Int,
    val name: String,
    val neighborhood: String,
    val photograph: String,
    val address: String,
    val cuisine_type: String,
    @Relation(
        parentColumn = "id",
        entityColumn = "restaurantId"
    )
    val latLng: LatLng,
    @Relation(
        parentColumn = "id",
        entityColumn = "restaurantId"
    )
    val timings: List<Timings>,
    @Relation(
        parentColumn = "id",
        entityColumn = "restaurantId"
    )
    val reviews: List<Reviews>,
)

@DatabaseView(
    """
    Select R.id , R.name , R.address , R.cuisine_type, M.id as menuId
    From Restaurants As R INNER JOIN 
    Menu As M ON R.id = M.restarantId
"""
)
data class RestaurantMenuDetails(
    val id: Int,
    val name: String,
    val address: String,
    val cuisine_type: String,
    val menuId: Int,
    @Relation(
        parentColumn = "menuId",
        entityColumn = "menuId"
    )
    val categories: List<Categories>
)