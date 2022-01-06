package com.example.rickandmortykotlin22.servicelocator

import com.example.rickandmortykotlin22.data.network.RetrofitClient
import com.example.rickandmortykotlin22.data.repositories.CharacterRepository
import com.example.rickandmortykotlin22.data.repositories.EpisodeRepository
import com.example.rickandmortykotlin22.data.repositories.LocationRepository
import com.example.rickandmortykotlin22.ui.fragment.character.CharacterViewModel
import com.example.rickandmortykotlin22.ui.fragment.character.datailcharacter.CharacterDetailViewModel
import com.example.rickandmortykotlin22.ui.fragment.episode.EpisodeViewModel
import com.example.rickandmortykotlin22.ui.fragment.episode.detailepisode.EpisodeDetailViewModel
import com.example.rickandmortykotlin22.ui.fragment.location.LocationViewModel
import com.example.rickandmortykotlin22.ui.fragment.location.detaillocation.LocationDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val networkModule = module {
    single { RetrofitClient() }
    single { get<RetrofitClient>().provideCharacterApiService() }
    single { get<RetrofitClient>().provideEpisodeApiService() }
    single { get<RetrofitClient>().provideLocationApiService() }
}
val repositoriesModel = module {
    factory { CharacterRepository(get()) }
    factory { EpisodeRepository(get()) }
    factory { LocationRepository(get()) }
}

val viewModelModule = module {
    viewModel { CharacterViewModel(get()) }
    viewModel { CharacterDetailViewModel(get()) }
    viewModel { EpisodeViewModel(get()) }
    viewModel { EpisodeDetailViewModel(get()) }
    viewModel { LocationViewModel(get()) }
    viewModel { LocationDetailViewModel(get()) }
}