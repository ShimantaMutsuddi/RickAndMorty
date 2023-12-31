package com.chutyrooms.rickandmorty.ui.features.characters


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.chutyrooms.rickandmorty.data.repository.CharacterRepository
import com.example.rickandmorty.data.entities.Character
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@ExperimentalPagingApi
@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val repository: CharacterRepository
) : ViewModel() {

    //val characters = repository.getCharacters()
    val characters: LiveData<PagingData<Character>> =
        repository.getCharacters().cachedIn(viewModelScope)



}


