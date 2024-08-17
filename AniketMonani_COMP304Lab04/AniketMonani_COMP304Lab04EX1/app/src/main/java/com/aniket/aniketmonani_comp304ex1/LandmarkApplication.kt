package com.aniket.aniketmonani_comp304ex1

import android.app.Application
import com.aniket.aniketmonani_comp304ex1.database.AppDatabase

class LandmarkApplication:Application() {
    val database: AppDatabase by lazy { AppDatabase.getDatabase(this) }
}