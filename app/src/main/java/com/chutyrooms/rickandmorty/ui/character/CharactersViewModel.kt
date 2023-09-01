package com.chutyrooms.rickandmorty.ui.character


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chutyrooms.rickandmorty.data.repository.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val repository: CharacterRepository
) : ViewModel() {

    val characters = repository.getCharacters()
}
