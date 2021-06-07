package com.chintankhetiya.mvvm.architectural.webservice

import com.chintankhetiya.mvvm.architectural.model.UniversitiesEntity
import retrofit2.Call
import retrofit2.http.GET

interface APIFunctionList {
    @GET("search?name=middle")
    fun getUniversitiesList(): Call<List<UniversitiesEntity>>
}
