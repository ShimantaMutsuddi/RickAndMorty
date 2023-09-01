package com.chutyrooms.rickandmorty.data.remote

import com.chutyrooms.rickandmorty.data.remote.BaseDataSource
import com.example.rickandmorty.data.remote.CharacterService
import javax.inject.Inject

class CharacterRemoteDataSource @Inject constructor(
    private val characterService: CharacterService
): BaseDataSource() {

    suspend fun getCharacters() = getResult { characterService.getAllCharacters() }
    suspend fun getCharacter(id: Int) = getResult { characterService.getCharacter(id) }
}