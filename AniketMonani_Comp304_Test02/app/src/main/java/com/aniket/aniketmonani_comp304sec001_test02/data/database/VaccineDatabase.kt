package com.aniket.aniketmonani_comp304sec001_test02.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.aniket.aniketmonani_comp304sec001_test02.data.dao.PharmacyDao
import com.aniket.aniketmonani_comp304sec001_test02.data.dao.VaccineDetailsDao
import com.aniket.aniketmonani_comp304sec001_test02.data.entities.Pharmacy
import com.aniket.aniketmonani_comp304sec001_test02.data.entities.VaccineDetails

@Database(entities = [Pharmacy::class, VaccineDetails::class], version = 1, exportSchema = false)
abstract class VaccineDatabase : RoomDatabase() {

    abstract fun pharmacyDao(): PharmacyDao
    abstract fun vaccineDetailsDao(): VaccineDetailsDao

    companion object {
        @Volatile
        private var INSTANCE: VaccineDatabase? = null

        fun getDatabase(context: Context): VaccineDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    VaccineDatabase::class.java,
                    "vaccine_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}