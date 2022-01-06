package com.example.rickandmortykotlin22.ui.fragment.location.detaillocation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.example.rickandmortykotlin22.R
import com.example.rickandmortykotlin22.databinding.FragmentLocationDetailBinding
import com.example.rickandmortykotlin22.keeper.base.BaseFragment
import com.example.rickandmortykotlin22.presentation.state.UIState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LocationDetailFragment :
    BaseFragment<LocationDetailViewModel, FragmentLocationDetailBinding>(R.layout.fragment_location_detail) {

    private val viewModel: LocationDetailViewModel by viewModels()
    private var _binding: FragmentLocationDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLocationDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchLocation(LocationDetailFragmentArgs.fromBundle(requireArguments()).id)
        setupRequests()
    }

    private fun setupRequests() = with(binding) {
        viewModel.locationState.subscribe {
            progressBar.isVisible = it is UIState.Loading
            groupMain.isVisible = it !is UIState.Loading
            when (it) {
                is UIState.Loading -> {
                }
                is UIState.Error -> {
                    Toast.makeText(requireContext(), it.massage, Toast.LENGTH_SHORT).show()
                }
                is UIState.Success -> {
                    it.data?.let { data ->
                        name.text = data.toString()
                        type.text = data.toString()
                        dimension.text = data.toString()
                        created.text = data.toString()
                        url.text = data.toString()
                    }
                }
            }
        }
    }
}