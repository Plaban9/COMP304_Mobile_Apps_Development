package com.example.pb_jm_comp304sec003_lab03.roomDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlin.concurrent.Volatile

@Database(entities = [RoomCityData::class], version = 1)
abstract class CityDatabase : RoomDatabase() {
    abstract fun roomCityDao(): CityDAO

    companion object {
        @Volatile
        private var Instance: CityDatabase? = null

        fun getCityDatabase(context: Context): CityDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context = context,
                    klass = CityDatabase::class.java,
                    name = "city_database",
                ).build().also {
                    Instance = it
                }
            }
        }
    }
}