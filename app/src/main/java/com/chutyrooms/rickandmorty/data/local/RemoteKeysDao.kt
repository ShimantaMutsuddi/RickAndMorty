package com.chutyrooms.rickandmorty.data.local

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.chutyrooms.rickandmorty.data.model.CharacterRemoteKeys
import com.example.rickandmorty.data.entities.Character

@Dao
interface RemoteKeysDao {
    @Query("SELECT * FROM CharacterRemoteKeys WHERE id=:id")
    suspend fun getRemoteKeys(id: Int) : CharacterRemoteKeys

    /*Query("SELECT * FROM characters WHERE id = :id")
    fun getCharacter(id: Int): LiveData<Character>*/

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllRemoteKeys(characters: List<CharacterRemoteKeys>)


    @Query("DELETE FROM CharacterRemoteKeys")
    suspend fun deleteAllCharacterKeys()

    /*@Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(character: Character)*/
}