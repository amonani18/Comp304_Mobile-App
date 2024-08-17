package com.aniket.aniketmonani_comp304sec001_test02.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "vaccine_details_table")
data class VaccineDetails(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val pharmacyName: String,
    val date: String,
    val time: String
)