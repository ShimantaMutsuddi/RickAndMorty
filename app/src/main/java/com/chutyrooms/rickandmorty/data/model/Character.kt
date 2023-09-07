package com.example.rickandmorty.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.chutyrooms.pagingpractice.model.Location
import com.chutyrooms.pagingpractice.model.Origin
import com.google.gson.annotations.SerializedName
import java.io.Serializable


@Entity(tableName = "characters")
data class Character(
    val created: String,
    val gender: String,
    @PrimaryKey
    val id: Int,
    val image: String,
    val name: String,
    val species: String,
    val status: String,
    val type: String,
    val url: String,
    val location: Location,
    val origin: Origin
) : Serializable