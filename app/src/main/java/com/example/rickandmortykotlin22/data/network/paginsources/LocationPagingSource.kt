package com.example.rickandmortykotlin22.data.network.paginsources

import com.example.rickandmortykotlin22.keeper.base.BasePagingSource
import com.example.rickandmortykotlin22.data.network.apiservice.LocationApiService
import com.example.rickandmortykotlin22.data.network.dto.location.LocationDto

class LocationPagingSource(
    private val service: LocationApiService
) : BasePagingSource<LocationDto>({ position ->
    service.fetchLocations(position)
})