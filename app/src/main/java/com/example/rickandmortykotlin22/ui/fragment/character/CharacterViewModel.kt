package com.example.rickandmortykotlin22.ui.fragment.character

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.rickandmortykotlin22.data.repositories.CharacterRepository
import com.example.rickandmortykotlin22.keeper.base.BaseViewModel
import javax.inject.Inject


class CharacterViewModel @Inject constructor(
    private val repository: CharacterRepository
) : BaseViewModel() {

    fun fetchCharacters() = repository.fetchCharacters().cachedIn(viewModelScope)
}