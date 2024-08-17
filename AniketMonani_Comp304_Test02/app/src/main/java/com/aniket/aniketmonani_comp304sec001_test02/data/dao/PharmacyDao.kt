package com.aniket.aniketmonani_comp304sec001_test02.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aniket.aniketmonani_comp304sec001_test02.data.entities.Pharmacy

@Dao
interface PharmacyDao {
    @Insert
    suspend fun insert(pharmacy: Pharmacy): Long  // Returns the ID of the inserted item

    @Query("SELECT * FROM pharmacies")
    fun getAllPharmacies(): LiveData<List<Pharmacy>>
}
