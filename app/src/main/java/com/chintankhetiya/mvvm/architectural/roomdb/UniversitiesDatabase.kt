package com.chintankhetiya.mvvm.architectural.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.chintankhetiya.mvvm.architectural.model.UniversitiesEntity

@Database(entities = arrayOf(UniversitiesEntity::class), version = 1, exportSchema = true)
public abstract class UniversitiesDatabase : RoomDatabase() {

    abstract fun UniversitiesDao(): UniversitiesDao

    companion object {
        @Volatile
        private var INSTANCE: UniversitiesDatabase? = null

        fun getDatabaseClient(context: Context): UniversitiesDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UniversitiesDatabase::class.java,
                    "roomdb_universities"
                )
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}
