package com.example.rickandmortykotlin22.data.repositories

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.rickandmortykotlin22.data.network.apiservice.CharacterApiService
import com.example.rickandmortykotlin22.data.network.dto.character.CharacterDto
import com.example.rickandmortykotlin22.data.network.paginsources.CharacterPagingSource
import com.example.rickandmortykotlin22.keeper.base.BaseRepository
import javax.inject.Inject


class CharacterRepository @Inject constructor(
    private val service: CharacterApiService,
) : BaseRepository() {

    fun fetchCharacters(): LiveData<PagingData<CharacterDto>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
            ),
            pagingSourceFactory = {
                CharacterPagingSource(service)
            }
        ).liveData
    }

    fun fetchCharacter(id: Int) = doRequest {
        service.fetchCharacter(id)
    }
}