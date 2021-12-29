package com.example.rickandmortykotlin22.data.repositories

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.rickandmortykotlin22.data.network.apiservice.EpisodeApiService
import com.example.rickandmortykotlin22.data.network.dto.episode.EpisodeDto
import com.example.rickandmortykotlin22.data.network.paginsources.EpisodePagingSource
import com.example.rickandmortykotlin22.keeper.base.BaseRepository
import javax.inject.Inject

class EpisodeRepository @Inject constructor(
    private val service: EpisodeApiService,
) : BaseRepository() {

    fun fetchEpisodes(): LiveData<PagingData<EpisodeDto>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
            ),
            pagingSourceFactory = {
                EpisodePagingSource(service)
            }
        ).liveData
    }

    fun fetchEpisode(id: Int) = doRequest {
        service.fetchEpisode(id)
    }
}