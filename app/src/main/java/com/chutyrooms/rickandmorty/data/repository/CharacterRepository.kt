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
    /*fun getCharacters(): LiveData<Resource<PagingData<Character>>> {
        return flow {
            emit(Resource.loading(null)) // Emit a loading state initially

             Pager(
                config = PagingConfig(pageSize = 20, maxSize = 100),
                remoteMediator = CharacterRemoteMediator(characterService, appDatabase),
                pagingSourceFactory = { appDatabase.characterDao().getAllCharacters() }
            ).flow.collect {
                emit(Resource.success(it))
            }
        }.onStart {
            emit(Resource.loading(null)) // Emit a loading state initially
        }.catch { throwable ->
            emit(Resource.error("An error occurred", null))
        }.asLiveData()
    }*/



    /*fun getCharacters()= Pager(
        config = PagingConfig(pageSize = 20, maxSize = 100),
        remoteMediator = CharacterRemoteMediator(characterService, appDatabase),
        pagingSourceFactory = { appDatabase.characterDao().getAllCharacters() }

    ).liveData*/
  /*  fun getCharacters(): LiveData<Resource<PagingData<Character>>> {
        return Pager(
            config = PagingConfig(pageSize = 20, maxSize = 100),
            remoteMediator = CharacterRemoteMediator(characterService, appDatabase),
            pagingSourceFactory = { appDatabase.characterDao().getAllCharacters() }
        ).liveData.map { pagingData ->
            Resource.success(pagingData)
        }.onStart {
            emit(Resource.loading())
        }.catch { throwable ->
            emit(Resource.error("An error occurred", null))
        }.asLiveData()
    }

    fun getCharacterss(): LiveData<Resource<PagingData<Character>>>{return Pager(
            config = PagingConfig(pageSize = 20, maxSize = 100),
            remoteMediator = CharacterRemoteMediator(characterService, appDatabase),
            pagingSourceFactory = { appDatabase.characterDao().getAllCharacters() }
        ).liveData.map { pagingData ->
            Resource.loading(pagingData) // Emit a loading state initially
        }.catch { throwable ->
            emit(Resource.error("An error occurred", null))
        }.map { resource ->
            // You can perform additional operations here if needed
            resource
        }.asLiveData()










        *//* fun getCharacters(): LiveData<Resource<PagingData<Character>>> {
             val pagingData = Pager(
                 config = PagingConfig(pageSize = 20, maxSize = 100),
                 remoteMediator = CharacterRemoteMediator(characterService, appDatabase),
                 pagingSourceFactory = { appDatabase.characterDao().getAllCharacters() }
             ).liveData

             return Transformations.map(pagingData) { pagingData ->
                Resource.success(
                     pagingData,
                     totalPages = pagingData.source.refresh.getClosestPageToCurrentPosition(),
                     currentPage = pagingData.source.refresh.currentPage
                 )
             }
         }*//*




    *//*fun getCharacter(id: Int) = performGetOperation(
        databaseQuery = { localDataSource.getCharacter(id) },
        networkCall = { remoteDataSource.getCharacter(id) },
        saveCallResult = { localDataSource.insert(it) }
    )

    fun getCharacters() = performGetOperation(
        databaseQuery = { localDataSource.getAllCharacters() },
        networkCall = { remoteDataSource.getCharacters() },
        saveCallResult = { localDataSource.insertAll(it.results) }
    )*//*
}*/