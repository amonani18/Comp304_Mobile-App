package com.mishra.group15

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [AirLinesDB::class], version = 1)
abstract class AirLinesDatabase : RoomDatabase() {
    abstract fun AirLinesDAO() : AirLinesDAO
}