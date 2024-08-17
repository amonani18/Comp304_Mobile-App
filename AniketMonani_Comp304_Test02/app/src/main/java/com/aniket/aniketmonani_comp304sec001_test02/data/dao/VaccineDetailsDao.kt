package com.aniket.aniketmonani_comp304sec001_test02.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aniket.aniketmonani_comp304sec001_test02.data.entities.VaccineDetails

@Dao
interface VaccineDetailsDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(vaccineDetails: VaccineDetails)

    @Query("SELECT * FROM vaccine_details_table WHERE pharmacyName = :name")
    fun getVaccineDetailsByPharmacy(name: String): LiveData<VaccineDetails>
}