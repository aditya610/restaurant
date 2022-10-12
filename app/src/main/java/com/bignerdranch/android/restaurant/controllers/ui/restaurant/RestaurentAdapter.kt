package com.bignerdranch.android.restaurant.controllers.ui.restaurant

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuAdapter
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.bignerdranch.android.restaurant.R
import com.bignerdranch.android.restaurant.databinding.ListItemCategoriesBinding
import com.bignerdranch.android.restaurant.databinding.ListItemMenuItemsBinding
import com.bignerdranch.android.restaurant.databinding.ListItemRestaurentBinding
import com.bignerdranch.android.restaurant.models.data.CategoriesWithMenuItems
import com.bignerdranch.android.restaurant.models.data.RestaurantMenuDetails
import com.bignerdranch.android.restaurant.models.data.RestaurentWithMenu

class RestaurentAdapter : RecyclerView.Adapter<RetaurantHolder>() {

    private var oldData = emptyList<RestaurentRecyclerViewItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RetaurantHolder {
        return when (viewType) {
            R.layout.list_item_restaurent -> RetaurantHolder.RestaurantViewHolder(
                ListItemRestaurentBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            R.layout.list_item_categories -> RetaurantHolder.CategoriesViewHolder(
                ListItemCategoriesBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            R.layout.list_item_menu_items -> RetaurantHolder.MenuItemViewHolder(
                ListItemMenuItemsBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            else -> throw IllegalArgumentException("Invalid ViewType Provided")
        }
    }

    override fun onBindViewHolder(holder: RetaurantHolder, position: Int) {
        when (holder) {
            is RetaurantHolder.RestaurantViewHolder -> holder.bind(oldData[position] as RestaurentRecyclerViewItem.Restaurent)
            is RetaurantHolder.CategoriesViewHolder -> holder.bind(oldData[position] as RestaurentRecyclerViewItem.Categories)
            is RetaurantHolder.MenuItemViewHolder -> holder.bind(oldData[position] as RestaurentRecyclerViewItem.MenuItem)
            else -> {}
        }
        holder.itemView.setOnClickListener({
            when (holder) {
                is RetaurantHolder.RestaurantViewHolder -> holder.onClick(
                    oldData[position] as RestaurentRecyclerViewItem.Restaurent,
                    it
                )
                else -> {}
            }
        })

    }

    override fun getItemCount(): Int {
        return oldData.size
    }

    override fun getItemViewType(position: Int): Int {
        return when (oldData[position]) {
            is RestaurentRecyclerViewItem.Categories -> R.layout.list_item_categories
            is RestaurentRecyclerViewItem.MenuItem -> R.layout.list_item_menu_items
            is RestaurentRecyclerViewItem.Restaurent -> R.layout.list_item_restaurent
        }
    }

    fun setData(newData: List<RestaurentRecyclerViewItem>) {
        oldData = newData
        notifyDataSetChanged()
    }
}

sealed class RetaurantHolder(binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {

    class RestaurantViewHolder(private val binding: ListItemRestaurentBinding) :
        RetaurantHolder(binding) {
        fun bind(restaurant: RestaurentRecyclerViewItem.Restaurent) {
            binding.tvName.text = restaurant.RestaurantName
            binding.tvCusine.text = restaurant.cuisineType
            binding.tvLocation.text = restaurant.address
        }

        fun onClick(restaurent: RestaurentRecyclerViewItem.Restaurent, view: View) {
            val action =
                RestaurantFragmentDirections.actionRestaurantFragmentToRestaurantDetailFragment(
                    restaurent.id
                )
            view.findNavController().navigate(action)
        }
    }

    class CategoriesViewHolder(private val binding: ListItemCategoriesBinding) :
        RetaurantHolder(binding) {
        fun bind(categories: RestaurentRecyclerViewItem.Categories) {
            binding.tvCategories.text = categories.categoryName
        }
    }

    class MenuItemViewHolder(private val binding: ListItemMenuItemsBinding) :
        RetaurantHolder(binding) {
        fun bind(menuItem: RestaurentRecyclerViewItem.MenuItem) {
            binding.tvMenuItem.text = menuItem.menuItemName
        }
    }

}
