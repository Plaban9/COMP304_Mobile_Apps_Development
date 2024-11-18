package com.example.pb_jm_comp304sec003_lab03.roomDB

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "city_card")
data class RoomCityData(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,

    @ColumnInfo(name = "city_name")
    val name: String,
//
//    @ColumnInfo(name = "city_region")
//    val region: String,
//
//    @ColumnInfo(name = "city_country")
//    val country: String,
//
//    @ColumnInfo(name = "temp_c")
//    val tempC: Double,
//
//    @ColumnInfo(name = "humidity")
//    val humidity: Double,
//
//    @ColumnInfo(name = "feelslike_c")
//    val feelsLikeC: Double,
//
//    @ColumnInfo(name = "uv")
//    val uv: Double,
)
