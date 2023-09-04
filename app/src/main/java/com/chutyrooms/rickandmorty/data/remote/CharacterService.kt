package com.example.rickandmorty.data.remote

import com.example.rickandmorty.data.entities.Character
import com.example.rickandmorty.data.entities.CharacterList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharacterService {
   /* @GET("api/character")
    suspend fun getAllCharacters(): Response<CharacterList>
    //suspend fun getAllCharacters(@Query("page") page:Int): Response<CharacterList>
    @GET("api/character/{id}")
    suspend fun getCharacter(@Path("id") id: Int): Response<Character>*/

   @GET("api/character")
   suspend fun getAllCharacters(@Query("page") page:Int): Response<CharacterList>

    @GET("api/character/{id}")
    suspend fun getCharacter(@Path("id") id: Int): Response<Character>
}