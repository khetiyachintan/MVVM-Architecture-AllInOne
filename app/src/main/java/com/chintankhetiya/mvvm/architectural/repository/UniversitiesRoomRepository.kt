package com.chintankhetiya.mvvm.architectural.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.chintankhetiya.mvvm.architectural.model.UniversitiesEntity
import com.chintankhetiya.mvvm.architectural.roomdb.UniversitiesDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class UniversitiesRoomRepository() {

    companion object {

        var uniDatabase: UniversitiesDatabase? = null

        var uniEntity: LiveData<List<UniversitiesEntity>>? = null

        fun initDatabase(context: Context): UniversitiesDatabase {
            return UniversitiesDatabase.getDatabaseClient(context)
        }

        fun insertData(context: Context, name: String, country: String, code: String) {
            uniDatabase = initDatabase(context)
            CoroutineScope(IO).launch {
                val loginDetails = UniversitiesEntity(name, country, code)
                uniDatabase!!.UniversitiesDao().insertUniversity(loginDetails)
            }
        }

        fun getUniversitiesListFromRoom(context: Context): LiveData<List<UniversitiesEntity>> {
            uniDatabase = initDatabase(context)
            uniEntity = uniDatabase!!.UniversitiesDao().getUniversitiesFromRoom()
            return uniEntity as LiveData<List<UniversitiesEntity>>
        }
    }
}
