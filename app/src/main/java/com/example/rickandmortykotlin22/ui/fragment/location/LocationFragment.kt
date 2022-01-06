package com.example.rickandmortykotlin22.ui.fragment.location

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickandmortykotlin22.R
import com.example.rickandmortykotlin22.databinding.FragmentLocationBinding
import com.example.rickandmortykotlin22.keeper.base.BaseFragment
import com.example.rickandmortykotlin22.ui.adapter.LocationAdapter
import com.example.rickandmortykotlin22.ui.adapter.paging.LoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LocationFragment :
    BaseFragment<LocationViewModel, FragmentLocationBinding>(R.layout.fragment_location) {

    private lateinit var binding: FragmentLocationBinding
    private val viewModel: LocationViewModel by viewModels()
    private val locationAdapter = LocationAdapter(this::setupListeners)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLocationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun initialize() {
        setupLocationRecycler()
    }

    private fun setupLocationRecycler() = with(binding) {
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = locationAdapter.withLoadStateFooter(LoadStateAdapter {
            locationAdapter.retry()
        })

        locationAdapter.addLoadStateListener { loadStates ->
            recyclerView.isVisible = loadStates.refresh is LoadState.NotLoading
            progressBar.isVisible = loadStates.refresh is LoadState.Loading
            binding.swipeRefresh.isRefreshing =
                loadStates.refresh is LoadState.Loading
        }
    }

    override fun setupRequest() {
        lifecycleScope.launch {
            viewModel.fetchLocations().collectLatest {
                locationAdapter.submitData(it)
            }
        }
    }


    private fun setupListeners(id: Int) {
        findNavController().navigate(
            LocationFragmentDirections.actionLocationFragmentToLocationDetailFragment(id)
        )
    }

    override fun swipeRefresh() {
        binding.swipeRefresh.setOnRefreshListener { locationAdapter.refresh() }
    }
}