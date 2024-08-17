package com.aniket.aniketmonani_comp304ex1.database

import androidx.room.Dao
import androidx.room.Query
import androidx.lifecycle.LiveData
@Dao
interface LandmarkDao {
    @Query("SELECT * FROM landmarks WHERE type = :type")
    fun getLandmarksByType(type: String): LiveData<List<LandmarkEntity>>



}

