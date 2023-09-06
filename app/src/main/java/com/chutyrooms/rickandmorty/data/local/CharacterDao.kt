package com.chutyrooms.rickandmorty.data.local

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rickandmorty.data.entities.Character

@Dao
interface CharacterDao {

    @Query("SELECT * FROM characters")
    fun getAllCharacters(): PagingSource<Int, Character>

    @Query("SELECT * FROM characters WHERE id = :id")
    fun getCharacter(id: Int): PagingSource<Int, Character>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(characters: List<Character>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(character: Character)

    @Query("DELETE FROM characters")
    suspend fun deleteAllCharacter()


}