package com.chintankhetiya.mvvm.architectural.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.chintankhetiya.mvvm.architectural.model.UniversitiesEntity
import com.chintankhetiya.mvvm.architectural.repository.UniversitiesRoomRepository
import com.chintankhetiya.mvvm.architectural.repository.UniversitiesAPIRepository

class UniversitiesViewModel(application: Application) : AndroidViewModel(application) {
    lateinit var liveDataUniversitiesAPI: MutableLiveData<List<UniversitiesEntity>>
    lateinit var liveDataUniversitiesRoom: LiveData<List<UniversitiesEntity>>
    lateinit var apiUniversities: UniversitiesAPIRepository

    /*Init API Object*/
    fun initAPI() {
        apiUniversities = UniversitiesAPIRepository.getInstance()
        liveDataUniversitiesAPI = apiUniversities.getUniversities()
    }

    fun insertData(context: Context, name: String, country: String, code: String) {
        UniversitiesRoomRepository.insertData(context, name, country, code)
    }

    fun getUniversitiesList(context: Context): LiveData<List<UniversitiesEntity>> {
        liveDataUniversitiesRoom = UniversitiesRoomRepository.getUniversitiesListFromRoom(context)
        return liveDataUniversitiesRoom
    }

}
