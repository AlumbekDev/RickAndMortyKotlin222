package com.example.rickandmortykotlin22.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.rickandmortykotlin22.data.network.apiservice.LocationApiService
import com.example.rickandmortykotlin22.data.network.dto.location.LocationDto
import com.example.rickandmortykotlin22.data.network.paginsources.LocationPagingSource
import com.example.rickandmortykotlin22.keeper.base.BaseRepository
import kotlinx.coroutines.flow.Flow

class LocationRepository(
    private val service: LocationApiService
) : BaseRepository() {

    fun fetchLocations(): Flow<PagingData<LocationDto>> {
        return Pager(
            config = PagingConfig(
                pageSize = 5
            ),
            pagingSourceFactory = {
                LocationPagingSource(service)
            }
        ).flow
    }

    fun fetchLocation(id: Int) = doRequest {
        service.fetchLocation(id)
    }
}