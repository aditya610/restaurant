package com.bignerdranch.android.restaurant.models.data

object SeedData {
    val restaurantsList = listOf(
        Restaurants(
            1,
            "Street Chinese Food",
            "Manhattan",
            "1.jpg",
            "171 E Broadway, New York, NY 10002",
            "Asian, Chinese"
        ),
        Restaurants(
            2,
            "La Pino Itallian Food",
            "New York",
            "2.jpg",
            "172 E Highway, New York, NY 10002",
            "Itallian, Chinese"
        ),
        Restaurants(
            3,
            "Haldiram",
            "Delhi",
            "2.jpg",
            "Cp Delhi India",
            "Indian"
        )
    )

    val latLngList = listOf(
        LatLng(
            200,
            1,
            40.733f,
            73.9899f
        ),
        LatLng(
            201,
            2,
            30.733f,
            83.9899f
        ),
        LatLng(
            202,
            3,
            30.733f,
            83.9899f
        ),
    )

    val timingsList = listOf(
        Timings(
            300,
            1,
            "Monday",
            "5:30pm - 11.00pm"
        ),
        Timings(
            301,
            1,
            "Tuesday",
            "5:30pm - 11.00pm"
        ),
        Timings(
            302,
            2,
            "Monday",
            "5:30pm - 11.00pm"
        ),
        Timings(
            303,
            2,
            "Tuesday",
            "5:30pm - 11.00pm"
        ), Timings(
            304,
            3,
            "Monday",
            "5:30pm - 11.00pm"
        ),
        Timings(
            305,
            3,
            "Tuesday",
            "5:30pm - 11.00pm"
        ), Timings(
            306,
            3,
            "Wednesday",
            "5:30pm - 11.00pm"
        ),
        Timings(
            307,
            3,
            "Thursday",
            "5:30pm - 11.00pm"
        )
    )

    val reviewsList = listOf(
        Reviews(
            401,
            1,
            "Jason",
            "October 26, 2020",
            3,
            "I was VERY excited to come here after seeing and hearing so many good things"
        ),
        Reviews(
            402,
            1,
            "Morgan",
            "October 26, 2020",
            3,
            "I was VERY excited to come here after seeing and hearing so many good things"
        ),
        Reviews(
            403,
            2,
            "Steve",
            "October 26, 2020",
            3,
            "I was VERY excited to come here after seeing and hearing so many good things"
        ),
        Reviews(
            404,
            2,
            "Jason",
            "October 26, 2020",
            3,
            "I was VERY excited to come here after seeing and hearing so many good things"
        ),
        Reviews(
            405,
            3,
            "Jason",
            "October 26, 2020",
            3,
            "I was VERY excited to come here after seeing and hearing so many good things"
        )
    )

    val menuList = listOf(
        Menu(
            101,
            1
        ),
        Menu(
            102,
            2
        ),
        Menu(
            103,
            3
        )
    )

    val categories = listOf(
        Categories(
            26576,
            101,
            "Appeteasers"
        ),
        Categories(
            26577,
            101,
            "Main Course"
        ),
        Categories(
            26578,
            102,
            "Appeteasers"
        ),
        Categories(
            26579,
            102,
            "Main Course"
        ),
        Categories(
            26580,
            103,
            "Main Course"
        )
    )

    val menuItemsList = listOf(
        MenuItems(
            94349,
            26576,
            "Momo",
            "panner momo with chilli garlic",
            22.0f,
            ""
        ),
        MenuItems(
            94350,
            26576,
            "Pizza",
            "Veg Pizza with mushroom",
            52.0f,
            ""
        ),
        MenuItems(
            94351,
            26577,
            "Noodles",
            "half plate with chilli sause",
            22.0f,
            ""
        ),
        MenuItems(
            94352,
            26577,
            "Boiled Chicken",
            "full plate with rice",
            42.0f,
            ""
        ), MenuItems(
            94353,
            26578,
            "Burger",
            "veg burger with large tikki",
            22.0f,
            ""
        ),
        MenuItems(
            94354,
            26578,
            "Pizza",
            "Veg Pizza with cheese",
            52.0f,
            ""
        ),
        MenuItems(
            94355,
            26579,
            "Chicken Fry",
            "half plate with noodles",
            22.0f,
            ""
        ),
        MenuItems(
            94356,
            26579,
            "Boiled Chicken",
            "full plate with rice",
            42.0f,
            ""
        ), MenuItems(
            94357,
            26580,
            "Dosa",
            "Plain Dosa",
            42.0f,
            ""
        ), MenuItems(
            94358,
            26580,
            "Thalli",
            "roti , rice and dal full veg thalli",
            42.0f,
            ""
        )
    )

    val Timing = "Timings"
    val Review = "Reviews"
}