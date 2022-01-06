package com.example.rickandmortykotlin22.ui.fragment.location

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.rickandmortykotlin22.data.repositories.LocationRepository
import com.example.rickandmortykotlin22.keeper.base.BaseViewModel
import javax.inject.Inject


class LocationViewModel @Inject constructor(
    private val repository: LocationRepository
) : BaseViewModel() {

    fun fetchLocations() = repository.fetchLocations().cachedIn(viewModelScope)
}