package com.chutyrooms.rickandmorty.di

import android.content.Context
import androidx.room.Room
import com.chutyrooms.rickandmorty.data.local.AppDatabase
import com.chutyrooms.rickandmorty.utils.Constants.BASE_URL
import com.chutyrooms.rickandmorty.utils.Constants.DATABASE
import com.example.rickandmorty.data.remote.CharacterService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson) : Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideCharacterService(retrofit: Retrofit): CharacterService = retrofit.create(
        CharacterService::class.java)

    /*@Singleton
    @Provides
    fun provideCharacterRemoteDataSource(characterService: CharacterService) = CharacterRemoteDataSource(characterService)*/

   /* @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) = AppDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideCharacterDao(db: AppDatabase) = db.characterDao()*/


    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) : AppDatabase{
        return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE)
            .build()
    }

    /*@Singleton
    @Provides
    fun provideRepository(remoteDataSource: CharacterRemoteDataSource,
                          localDataSource: CharacterDao
    ) = CharacterRepository(remoteDataSource, localDataSource)*/
}