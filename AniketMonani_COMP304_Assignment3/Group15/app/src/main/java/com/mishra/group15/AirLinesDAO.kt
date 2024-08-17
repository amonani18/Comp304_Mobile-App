package com.mishra.group15

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface AirLinesDAO {
    @Query("DELETE From airlines")
    fun deleteAll()

    @Query("INSERT INTO airlines (airlineName, arrivalTime, terminalNumber, status) VALUES (:airlineName, :arrivalTime, :terminal, :status)")
    fun insertAirLine(airlineName: String, arrivalTime: String, terminal: String, status: Boolean)

    @Query("SELECT * FROM airlines")
    fun getAirLines(): LiveData<List<AirLinesDB>>

    @Query("SELECT * FROM airlines WHERE airlineName = :airlineName")
    fun getAirLinesWithName(airlineName: String): LiveData<List<AirLinesDB>>
}