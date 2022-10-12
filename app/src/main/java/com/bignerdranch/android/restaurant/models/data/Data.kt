package com.bignerdranch.android.restaurant.models.data

import androidx.room.*

@Entity(
    tableName = "Restaurants"
)
data class Restaurants(
    @PrimaryKey val id: Int,
    val name: String,
    val neighborhood: String,
    val photograph: String,
    val address: String,
    val cuisine_type: String

)


@Entity(
    tableName = "LatLng",
    foreignKeys = [
        ForeignKey(
            childColumns = ["restaurantId"],
            entity = Restaurants::class,
            parentColumns = ["id"]
        )
    ],
    indices = [
        Index("restaurantId")
    ]
)
data class LatLng(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val restaurantId: Int,
    val lat: Float,
    val lng: Float
)


@Entity(
    tableName = "Timings",
    foreignKeys = [
        ForeignKey(
            childColumns = ["restaurantId"],
            entity = Restaurants::class,
            parentColumns = ["id"]
        )
    ],
    indices = [
        Index("restaurantId")
    ]
)
data class Timings(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val restaurantId: Int,
    val day: String,
    val time: String
)


@Entity(
    tableName = "Reviews",
    foreignKeys = [
        ForeignKey(
            childColumns = ["restaurantId"],
            entity = Restaurants::class,
            parentColumns = ["id"]
        )
    ],
    indices = [
        Index("restaurantId")
    ]
)
data class Reviews(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val restaurantId: Int,
    val name: String,
    val date: String,
    val rating: Int,
    val comments: String
)


@Entity(
    tableName = "Menu"
)
data class Menu(
    @PrimaryKey
    val id: Int,
    val restarantId: Int
)


@Entity(
    tableName = "Categories",
    foreignKeys = [
        ForeignKey(
            childColumns = ["menuId"],
            entity = Menu::class,
            parentColumns = ["id"]
        )
    ],
    indices = [
        Index("menuId")
    ]
)
data class Categories(
    @PrimaryKey
    val id: Int,
    val menuId: Int,
    val name: String
)


@Entity(
    tableName = "MenuItems",
    foreignKeys = [
        ForeignKey(
            childColumns = ["categoriesId"],
            entity = Categories::class,
            parentColumns = ["id"]
        )
    ],
    indices = [
        Index("categoriesId")
    ]
)
data class MenuItems(
    @PrimaryKey
    val id: Int,
    val categoriesId: Int,
    val name: String,
    val description: String,
    val price: Float,
    val images: String
)


data class CategoriesWithMenuItems(
    @Embedded val category: Categories,
    @Relation(
        parentColumn = "id",
        entityColumn = "categoriesId"
    )
    val menuList: List<MenuItems>
)


data class MenuWithCategories(
    @Embedded val menu: Menu,
    @Relation(
        parentColumn = "id",
        entityColumn = "menuId"
    )
    val categoriesList: List<Categories>
)

data class RestaurentWithMenu(
    val restaurentDetail: RestaurantMenuDetails,
    val menuDetail: List<CategoriesWithMenuItems>
)