package com.chutyrooms.rickandmorty.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.map
import androidx.paging.*
import com.chutyrooms.rickandmorty.data.local.AppDatabase
import com.chutyrooms.rickandmorty.data.paging.CharacterRemoteMediator
import com.chutyrooms.rickandmorty.utils.Resource
import com.example.rickandmorty.data.entities.Character
import com.example.rickandmorty.data.remote.CharacterService
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@ExperimentalPagingApi
class CharacterRepository @Inject constructor(
    private val characterService: CharacterService,
    private val appDatabase: AppDatabase
) {

    fun getCharacters() = Pager(
        config = PagingConfig(pageSize = 20, maxSize = 100),
        remoteMediator = CharacterRemoteMediator(characterService, appDatabase),
        pagingSourceFactory = { appDatabase.characterDao().getAllCharacters() }

    ).liveData
}
