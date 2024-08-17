package com.aniket.aniketmonani_comp304sec001_test02.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.aniket.aniketmonani_comp304sec001_test02.data.database.VaccineDatabase
import com.aniket.aniketmonani_comp304sec001_test02.data.entities.Pharmacy
import com.aniket.aniketmonani_comp304sec001_test02.data.entities.VaccineDetails
import com.aniket.aniketmonani_comp304sec001_test02.repository.VaccineRepository
import kotlinx.coroutines.launch

class VaccineViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: VaccineRepository
    val allPharmacies: LiveData<List<Pharmacy>>

    init {
        try {
            // Retrieve DAOs from the singleton instance of the Room database
            val pharmacyDao = VaccineDatabase.getDatabase(application).pharmacyDao()
            val vaccineDetailsDao = VaccineDatabase.getDatabase(application).vaccineDetailsDao()

            // Initialize the repository with the DAOs
            repository = VaccineRepository(pharmacyDao, vaccineDetailsDao)

            // Get all pharmacies observable from the repository
            allPharmacies = repository.allPharmacies
        } catch (e: Exception) {
            // Log the exception to Android's log system
            Log.e("VaccineViewModel", "Error initializing database or repository", e)

            // Rethrow the exception to crash the app and indicate a fatal configuration error
            throw RuntimeException("Error initializing database or repository", e)
        }
    }

    fun insertPharmacy(pharmacy: Pharmacy) = viewModelScope.launch {
        repository.insertPharmacy(pharmacy)
    }

    fun getVaccineDetailsByPharmacy(name: String): LiveData<VaccineDetails> {
        return repository.getVaccineDetailsByPharmacy(name)
    }

    fun insertVaccineDetails(vaccineDetails: VaccineDetails) = viewModelScope.launch {
        repository.insertVaccineDetails(vaccineDetails)
    }
}
