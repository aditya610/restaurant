package com.bignerdranch.android.restaurant.controllers.ui.restaurantdetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.bignerdranch.android.restaurant.R
import com.bignerdranch.android.restaurant.controllers.ui.restaurant.RestaurentRecyclerViewItem
import com.bignerdranch.android.restaurant.databinding.ListItemCategoriesBinding
import com.bignerdranch.android.restaurant.databinding.ListItemMenuItemsDetailBinding
import com.bignerdranch.android.restaurant.databinding.ListItemReviewsBinding
import com.bignerdranch.android.restaurant.databinding.ListItemTimingsBinding

class RestaurentDetailAdapter : RecyclerView.Adapter<RetaurantDetailHolder>() {

    var items = emptyList<RestaurentDetailRecyclerViewItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RetaurantDetailHolder {
        return when (viewType) {
            R.layout.list_item_categories -> RetaurantDetailHolder.CategoriesViewHolder(
                ListItemCategoriesBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            R.layout.list_item_menu_items_detail -> RetaurantDetailHolder.MenuItemViewHolder(
                ListItemMenuItemsDetailBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            R.layout.list_item_reviews -> RetaurantDetailHolder.ReviewsItemViewHolder(
                ListItemReviewsBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            R.layout.list_item_timings -> RetaurantDetailHolder.TimmingViewHolder(
                ListItemTimingsBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            else -> throw IllegalArgumentException("Invalid ViewType Provided")
        }
    }

    override fun onBindViewHolder(holder: RetaurantDetailHolder, position: Int) {
        when (holder) {
            is RetaurantDetailHolder.CategoriesViewHolder -> holder.bind(items[position] as RestaurentDetailRecyclerViewItem.Categories)
            is RetaurantDetailHolder.MenuItemViewHolder -> holder.bind(items[position] as RestaurentDetailRecyclerViewItem.MenuItems)
            is RetaurantDetailHolder.ReviewsItemViewHolder -> holder.bind(items[position] as RestaurentDetailRecyclerViewItem.Reviews)
            is RetaurantDetailHolder.TimmingViewHolder -> holder.bind(items[position] as RestaurentDetailRecyclerViewItem.Timings)
        }
    }

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is RestaurentDetailRecyclerViewItem.Categories -> R.layout.list_item_categories
            is RestaurentDetailRecyclerViewItem.MenuItems -> R.layout.list_item_menu_items_detail
            is RestaurentDetailRecyclerViewItem.Reviews -> R.layout.list_item_reviews
            is RestaurentDetailRecyclerViewItem.Timings -> R.layout.list_item_timings
        }
    }

    fun setData(newData: List<RestaurentDetailRecyclerViewItem>) {
        items = newData
        notifyDataSetChanged()
    }

}

sealed class RetaurantDetailHolder(binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {

    class TimmingViewHolder(private val binding: ListItemTimingsBinding) :
        RetaurantDetailHolder(binding) {
        fun bind(timming: RestaurentDetailRecyclerViewItem.Timings) {
            binding.day.text = timming.timings.day
            binding.timmings.text = timming.timings.time
        }
    }

    class CategoriesViewHolder(private val binding: ListItemCategoriesBinding) :
        RetaurantDetailHolder(binding) {
        fun bind(categories: RestaurentDetailRecyclerViewItem.Categories) {
            binding.tvCategories.text = categories.categories
        }
    }

    class MenuItemViewHolder(private val binding: ListItemMenuItemsDetailBinding) :
        RetaurantDetailHolder(binding) {
        fun bind(menuItem: RestaurentDetailRecyclerViewItem.MenuItems) {
            binding.itemName.text = menuItem.menu.name
            binding.itemPrice.text = "Rs " + menuItem.menu.price.toString()
            binding.itemDecription.text = menuItem.menu.description
        }
    }

    class ReviewsItemViewHolder(private val binding: ListItemReviewsBinding) :
        RetaurantDetailHolder(binding) {
        fun bind(reviewsItem: RestaurentDetailRecyclerViewItem.Reviews) {
            binding.name.text = reviewsItem.reviews.name
            binding.comment.text = reviewsItem.reviews.comments
            binding.date.text = reviewsItem.reviews.date
        }

    }

}