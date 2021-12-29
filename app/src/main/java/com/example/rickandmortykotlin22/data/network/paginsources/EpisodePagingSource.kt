package com.example.rickandmortykotlin22.data.network.paginsources

import com.example.rickandmortykotlin22.data.network.apiservice.EpisodeApiService
import com.example.rickandmortykotlin22.data.network.dto.episode.EpisodeDto
import com.example.rickandmortykotlin22.keeper.base.BasePagingSource

class EpisodePagingSource(
    private val service: EpisodeApiService
) : BasePagingSource<EpisodeDto, Any?>({ position ->
    service.fetchEpisodes(position)
})