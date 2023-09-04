package com.chutyrooms.rickandmorty.data.paging
import android.provider.MediaStore
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.chutyrooms.rickandmorty.data.local.AppDatabase
import com.chutyrooms.rickandmorty.data.model.CharacterRemoteKeys
import com.example.rickandmorty.data.entities.Character
import com.example.rickandmorty.data.remote.CharacterService

@OptIn(ExperimentalPagingApi::class)
class CharacterRemoteMediator(
    private val characterService: CharacterService,
    private val appDatabase: AppDatabase
) : RemoteMediator<Int, Character>() {

    private val characterDao=appDatabase.characterDao()
    private val characterRemoteKeysDao=appDatabase.remoteKeysDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Character>
    ): MediatorResult {




        return try {
            // 3->logic for stated - refreash, prepend , append
            val currentPage=when(loadType){
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextPage?.minus(1) ?: 1
                }
                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val prevPage = remoteKeys?.prevPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    prevPage
                }
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextPage = remoteKeys?.nextPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    nextPage
                }
            }
            // 1->fetch character from api
            val response=characterService.getAllCharacters(currentPage)
            val endOfPaginationReached= response.body()?.info?.pages == currentPage

            val prevPage=if(currentPage==1) null else currentPage-1
            val nextPage=if(endOfPaginationReached) null else currentPage+1

            // 2->save the characters + remoteKeys data into db
            appDatabase.withTransaction {

                if(loadType == LoadType.REFRESH){
                    characterDao.deleteAllCharacter()
                    characterRemoteKeysDao.deleteAllCharacterKeys()
                }
                characterDao.insertAll(response.body()!!.results)

                val keys= response.body()!!.results.map{character->
                    CharacterRemoteKeys(
                        id=character.id,
                        prevPage =  prevPage,
                       nextPage =  nextPage
                    )



                }
                characterRemoteKeysDao.insertAllRemoteKeys(keys)


            }
            MediatorResult.Success(endOfPaginationReached )

        }catch (e:Exception)
        {
            MediatorResult.Error(e)

        }


    }
    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int,Character >
    ): CharacterRemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                characterRemoteKeysDao.getRemoteKeys(id = id)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, Character>
    ): CharacterRemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { character ->
                characterRemoteKeysDao.getRemoteKeys(id = character.id)
            }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, Character>
    ): CharacterRemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { character ->
                characterRemoteKeysDao.getRemoteKeys(id = character.id)
            }
    }
}