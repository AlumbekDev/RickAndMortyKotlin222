package com.example.rickandmortykotlin22.ui.fragment.episode

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.rickandmortykotlin22.data.repositories.EpisodeRepository
import com.example.rickandmortykotlin22.keeper.base.BaseViewModel
import javax.inject.Inject


class EpisodeViewModel @Inject constructor(
    private val repository: EpisodeRepository
) : BaseViewModel() {

    fun fetchEpisodes() = repository.fetchEpisodes().cachedIn(viewModelScope)
}