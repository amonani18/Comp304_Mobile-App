package com.aniket.aniketmonani_comp304sec001_test02.repository


import androidx.lifecycle.LiveData
import com.aniket.aniketmonani_comp304sec001_test02.data.dao.PharmacyDao
import com.aniket.aniketmonani_comp304sec001_test02.data.dao.VaccineDetailsDao
import com.aniket.aniketmonani_comp304sec001_test02.data.entities.Pharmacy
import com.aniket.aniketmonani_comp304sec001_test02.data.entities.VaccineDetails

class VaccineRepository(private val pharmacyDao: PharmacyDao, private val vaccineDetailsDao: VaccineDetailsDao) {

    val allPharmacies: LiveData<List<Pharmacy>> = pharmacyDao.getAllPharmacies()

    suspend fun insertPharmacy(pharmacy: Pharmacy) {
        pharmacyDao.insert(pharmacy)
    }

    fun getVaccineDetailsByPharmacy(name: String): LiveData<VaccineDetails> {
        return vaccineDetailsDao.getVaccineDetailsByPharmacy(name)
    }

    suspend fun insertVaccineDetails(vaccineDetails: VaccineDetails) {
        vaccineDetailsDao.insert(vaccineDetails)
    }
}