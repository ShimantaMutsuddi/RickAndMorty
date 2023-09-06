package com.chutyrooms.rickandmorty.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.chutyrooms.rickandmorty.data.model.CharacterRemoteKeys
import com.example.rickandmorty.data.entities.Character

@Database(
    entities = [Character::class, CharacterRemoteKeys::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(TypeConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun characterDao(): CharacterDao
    abstract fun remoteKeysDao(): RemoteKeysDao


}


