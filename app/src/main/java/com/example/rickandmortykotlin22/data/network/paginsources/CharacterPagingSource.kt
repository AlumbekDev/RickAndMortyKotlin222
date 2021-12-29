package com.example.rickandmortykotlin22.data.network.paginsources

import com.example.rickandmortykotlin22.data.network.apiservice.CharacterApiService
import com.example.rickandmortykotlin22.data.network.dto.character.CharacterDto
import com.example.rickandmortykotlin22.keeper.base.BasePagingSource

class CharacterPagingSource (
    private val service: CharacterApiService
) : BasePagingSource<CharacterDto, Any?>({ position ->
    service.fetchCharacters(position)
})