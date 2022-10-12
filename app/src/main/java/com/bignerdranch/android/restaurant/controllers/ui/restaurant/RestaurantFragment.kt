package com.bignerdranch.android.restaurant.controllers.ui.restaurant

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.bignerdranch.android.restaurant.R
import com.bignerdranch.android.restaurant.databinding.FragmentRestaurentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RestaurantFragment : Fragment() {

    private lateinit var binding: FragmentRestaurentBinding
    private val viewModel: RestaurantViewModel by viewModels()
    private lateinit var adapter: RestaurentAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel.intialize()
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_restaurent, container, false)
        binding.svSearchQuery.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                binding.svSearchQuery.clearFocus()
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                if (p0 != null) {
                    if (p0 == "") {
                        viewModel.data.value = viewModel.list.toList()
                    } else {
                        viewModel.searchDataList(p0)
                    }
                }
                return true
            }

        })
        adapter = RestaurentAdapter()
        binding.rvRestaurantList.layoutManager = LinearLayoutManager(context)
        binding.rvRestaurantList.adapter = adapter

        viewModel.data.observe(
            viewLifecycleOwner,
            Observer {
                if (it != null && it.size != 0) {
                    viewModel.setRecyclerAdapterList(it)
                } else {
                    viewModel.setRecyclerAdapterList(emptyList())
                }
            }
        )

        viewModel.adapterData.observe(
            viewLifecycleOwner
        ) {
            if (it != null && it.size != 0) {
                adapter.setData(it)
            } else {
                adapter.setData(emptyList())
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.restaurentCount.collect {
                        if (it != 0) {
                            viewModel.getData()
                        }
                    }
                }
            }
        }

        return binding.root

    }

}