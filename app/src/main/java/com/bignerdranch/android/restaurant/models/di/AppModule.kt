package com.bignerdranch.android.restaurant.models.di

import android.content.Context
import androidx.room.Room
import com.bignerdranch.android.restaurant.models.db.RestaurantDatabase
import com.bignerdranch.android.restaurant.models.db.dao.RestaurantDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private lateinit var appDatabase: RestaurantDatabase

    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext context: Context): RestaurantDatabase {
        appDatabase = Room.databaseBuilder(
            context,
            RestaurantDatabase::class.java,
            "restaurant-database"
        ).fallbackToDestructiveMigration().build()
        return appDatabase
    }

    @Provides
    fun provideRestaurantDao(appDatabase: RestaurantDatabase): RestaurantDao {
        return appDatabase.restaurantDao()
    }
}