package com.chutyrooms.rickandmorty.data.local

import androidx.room.TypeConverter
import com.chutyrooms.pagingpractice.model.Location
import com.chutyrooms.pagingpractice.model.Origin
import com.google.gson.Gson


class TypeConverter {
    @TypeConverter
    fun fromLocation(location: Location): String {
        return Gson().toJson(location)
    }

    @TypeConverter
    fun toLocation(locationString: String): Location {
        return Gson().fromJson(locationString, Location::class.java)
    }

    @TypeConverter
    fun fromOrigin(origin: Origin): String {
        return Gson().toJson(origin)
    }

    @TypeConverter
    fun toOrigin(originString: String): Origin {
        return Gson().fromJson(originString, Origin::class.java)
    }
}