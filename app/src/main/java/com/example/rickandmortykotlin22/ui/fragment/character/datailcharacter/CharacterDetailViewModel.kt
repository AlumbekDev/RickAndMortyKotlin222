package com.example.rickandmortykotlin22.ui.fragment.character.datailcharacter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortykotlin22.data.network.dto.character.CharacterDto
import com.example.rickandmortykotlin22.data.repositories.CharacterRepository
import com.example.rickandmortykotlin22.keeper.resource.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


class CharacterDetailViewModel @Inject constructor(
    private val repository: CharacterRepository,
) : ViewModel() {

    private val _characterState = MutableStateFlow<Resource<CharacterDto>>(Resource.Loading())
    val characterState: StateFlow<Resource<CharacterDto>> = _characterState
    fun fetchCharacter(id: Int) {

        viewModelScope.launch(Dispatchers.IO) {
            repository.fetchCharacter(id).collect {
                _characterState.value = it
            }
        }
    }
}