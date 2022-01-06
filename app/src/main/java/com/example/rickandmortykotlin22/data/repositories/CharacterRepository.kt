package com.example.rickandmortykotlin22.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.rickandmortykotlin22.data.network.apiservice.CharacterApiService
import com.example.rickandmortykotlin22.data.network.dto.character.CharacterDto
import com.example.rickandmortykotlin22.data.network.paginsources.CharacterPagingSource
import com.example.rickandmortykotlin22.keeper.base.BaseRepository
import kotlinx.coroutines.flow.Flow

class CharacterRepository (
    private val service: CharacterApiService
) : BaseRepository() {

    fun fetchCharacters(): Flow<PagingData<CharacterDto>> {
        return Pager(
            config = PagingConfig(
                pageSize = 5,
            ),
            pagingSourceFactory = {
                CharacterPagingSource(service)
            }
        ).flow
    }

    fun fetchCharacter(id: Int) = doRequest {
        service.fetchCharacter(id)
    }
}