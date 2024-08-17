package com.mishra.group15

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "airlines")
data class AirLinesDB(
    @PrimaryKey(autoGenerate = true)
    val id : Long,
    val airlineName: String,
    val arrivalTime: String,
    val terminalNumber: String,
    val status: Boolean
)