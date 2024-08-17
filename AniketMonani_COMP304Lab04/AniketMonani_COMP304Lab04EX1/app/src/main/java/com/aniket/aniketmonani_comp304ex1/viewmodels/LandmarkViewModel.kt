package com.aniket.aniketmonani_comp304ex1.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

import com.aniket.aniketmonani_comp304ex1.database.AppDatabase
import com.aniket.aniketmonani_comp304ex1.database.LandmarkEntity


class LandmarkViewModel(application: Application) : AndroidViewModel(application) {
    private val landmarkDao = AppDatabase.getDatabase(application).landmarkDao()

    fun getLandmarksByType(type: String): LiveData<List<LandmarkEntity>> {
        return landmarkDao.getLandmarksByType(type)
    }
}