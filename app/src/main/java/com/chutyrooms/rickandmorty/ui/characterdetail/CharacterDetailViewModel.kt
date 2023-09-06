package com.chutyrooms.rickandmorty.ui.characterdetail


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.paging.ExperimentalPagingApi
import com.chutyrooms.rickandmorty.data.repository.CharacterRepository
import com.chutyrooms.rickandmorty.utils.Resource
import com.example.rickandmorty.data.entities.Character
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@ExperimentalPagingApi
@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val repository: CharacterRepository
) : ViewModel() {

    private val _id = MutableLiveData<Int>()

    /* private val _character = _id.switchMap { id ->
         repository.getCharacter(id)
     }
     val character: LiveData<Resource<Character>> = _character*/


    /*   fun start(id: Int) {
           _id.value = id
       }*/

}
