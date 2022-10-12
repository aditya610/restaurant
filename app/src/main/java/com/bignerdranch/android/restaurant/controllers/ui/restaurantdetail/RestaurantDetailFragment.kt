package com.bignerdranch.android.restaurant.controllers.ui.restaurantdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bignerdranch.android.restaurant.R
import com.bignerdranch.android.restaurant.controllers.ui.restaurant.RestaurentAdapter
import com.bignerdranch.android.restaurant.databinding.FragmentRestaurantDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RestaurantDetailFragment : Fragment() {

    private lateinit var binding: FragmentRestaurantDetailBinding
    private val viewModel: RestaurantDetailViewModel by viewModels()
    private lateinit var adapter: RestaurentDetailAdapter
    private val args: RestaurantDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val id = args.id
        viewModel.id = id
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_restaurant_detail, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        adapter = RestaurentDetailAdapter()
        binding.rvRestaurantDetail.layoutManager = LinearLayoutManager(context)
        binding.rvRestaurantDetail.adapter = adapter

        viewModel.adapterData.observe(viewLifecycleOwner) {
            if (it != null && it.size != 0) {
                adapter.setData(it)
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.menuList.collect {
                        viewModel.setAdapter()
                    }
                }
            }
        }
        return binding.root
    }

}