package com.aniket.aniketmonani_comp304ex1.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// If you have migrations, import androidx.room.migration.Migration and androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [LandmarkEntity::class], version = 3)
abstract class AppDatabase : RoomDatabase() {
    abstract fun landmarkDao(): LandmarkDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "landmark_database")
                    .createFromAsset("database/landmark.db")
                    .build()
                INSTANCE = instance
                instance
            }
        }

    }
}

