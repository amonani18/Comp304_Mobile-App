package com.aniket.aniketmonani_comp304sec001_test02.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pharmacies")
data class Pharmacy(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val location: String
)
