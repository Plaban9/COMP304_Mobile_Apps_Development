package com.example.pb_jm_comp304sec003_lab03.roomDB

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CityDAO {

    @Query("SELECT * FROM city_card")
    suspend fun getAll(): List<RoomCityData>

    @Insert
    suspend fun insertCity(roomCityData: RoomCityData)
}