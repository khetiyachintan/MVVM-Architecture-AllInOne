package com.chintankhetiya.mvvm.architectural.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.chintankhetiya.mvvm.architectural.model.UniversitiesEntity
import com.chintankhetiya.mvvm.architectural.constant.UtilConstant
import com.chintankhetiya.mvvm.architectural.webservice.APIFunctionList
import com.chintankhetiya.mvvm.architectural.webservice.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class UniversitiesAPIRepository {
    lateinit var mapifunctionlist: APIFunctionList

    companion object {
        var objUniversitiesApi = UniversitiesAPIRepository()
        fun getInstance(): UniversitiesAPIRepository {
            objUniversitiesApi = UniversitiesAPIRepository()
            return objUniversitiesApi
        }
    }

    fun getUniversities(): MutableLiveData<List<UniversitiesEntity>> {
        val objTTRepoData = MutableLiveData<List<UniversitiesEntity>>()
        try {
            mapifunctionlist = RetrofitService.createService(APIFunctionList::class.java)

            val call: Call<List<UniversitiesEntity>> = mapifunctionlist.getUniversitiesList()

            call.enqueue {
                onResponse = {
                    if (it.isSuccessful) {
                        val data: List<UniversitiesEntity>? = it.body();
                        objTTRepoData.setValue(data)
                    }
                }

                onFailure = {
                    objTTRepoData.setValue(null)
                }
            }
        } catch (Ex: Exception) {
            Log.v(UtilConstant.LOG_TAG, "getUniversities Exception" + Ex.toString())
        }
        return objTTRepoData
    }


    fun <T> Call<T>.enqueue(callback: CallBackKt<T>.() -> Unit) {
        val callBackKt = CallBackKt<T>()
        callback.invoke(callBackKt)
        this.enqueue(callBackKt)
    }
    class CallBackKt<T> : Callback<T> {

        var onResponse: ((Response<T>) -> Unit)? = null
        var onFailure: ((t: Throwable?) -> Unit)? = null

        override fun onFailure(call: Call<T>, t: Throwable) {
            onFailure?.invoke(t)
        }

        override fun onResponse(call: Call<T>, response: Response<T>) {
            onResponse?.invoke(response)
        }

    }


}




