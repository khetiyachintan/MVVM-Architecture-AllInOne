package com.chintankhetiya.mvvm.architectural.roomdb

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.chintankhetiya.mvvm.architectural.model.UniversitiesEntity

@Dao
interface UniversitiesDao {

    @Query("SELECT * from tb_universities ORDER BY name ASC")
    fun getUniversitiesFromRoom(): LiveData<List<UniversitiesEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUniversity(item: UniversitiesEntity)

    @Query("DELETE FROM tb_universities")
    suspend fun deleteRoomAll()
}