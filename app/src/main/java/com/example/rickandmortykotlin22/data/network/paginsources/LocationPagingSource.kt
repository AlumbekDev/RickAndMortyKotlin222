package com.example.rickandmortykotlin22.data.network.paginsources

import com.example.rickandmortykotlin22.data.network.apiservice.LocationApiService
import com.example.rickandmortykotlin22.data.network.dto.location.LocationDto
import com.example.rickandmortykotlin22.keeper.base.BasePagingSource

class LocationPagingSource(
    private val service: LocationApiService
) : BasePagingSource<LocationDto, Any?>({ position ->
    service.fetchLocations(position)
})