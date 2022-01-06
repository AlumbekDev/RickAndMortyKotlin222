package com.example.rickandmortykotlin22.ui.fragment.location.detaillocation

import com.example.rickandmortykotlin22.data.network.dto.location.LocationDto
import com.example.rickandmortykotlin22.data.repositories.LocationRepository
import com.example.rickandmortykotlin22.keeper.base.BaseViewModel
import com.example.rickandmortykotlin22.presentation.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class LocationDetailViewModel @Inject constructor(
    private val repository: LocationRepository
) : BaseViewModel() {

    private val _locationState = MutableStateFlow<UIState<LocationDto>>(UIState.Loading())
    val locationState: StateFlow<UIState<LocationDto>> = _locationState

    fun fetchLocation(id: Int) {
        _locationState.subscribeTo {
            repository.fetchLocation(id)
        }
    }
}