package com.example.rickandmortykotlin22.ui.fragment.episode.detailepisode

import com.example.rickandmortykotlin22.data.network.dto.episode.EpisodeDto
import com.example.rickandmortykotlin22.data.repositories.EpisodeRepository
import com.example.rickandmortykotlin22.keeper.base.BaseViewModel
import com.example.rickandmortykotlin22.presentation.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@HiltViewModel
class EpisodeDetailViewModel(
    private val repository: EpisodeRepository
) : BaseViewModel() {

    private val _episodeState = MutableStateFlow<UIState<EpisodeDto>>(UIState.Loading())
    val episodeState: StateFlow<UIState<EpisodeDto>> = _episodeState

    fun fetchEpisode(id: Int) {
        _episodeState.subscribeTo {
            repository.fetchEpisode(id)
        }
    }
}