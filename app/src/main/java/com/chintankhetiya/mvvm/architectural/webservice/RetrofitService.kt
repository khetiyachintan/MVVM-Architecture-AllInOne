package com.chintankhetiya.mvvm.architectural.webservice

import android.annotation.SuppressLint
import com.chintankhetiya.mvvm.architectural.utils.AppUtility
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit


internal object RetrofitService {

    private val objRetrofit = Retrofit.Builder()
        .baseUrl(APIConstant.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(onGetBuilder().build())
        .build()

    fun <S> createService(serviceClass: Class<S>?): S {
        return objRetrofit.create(serviceClass)
    }

    @SuppressLint("SdCardPath")
    fun onGetBuilder(): OkHttpClient.Builder {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        var httpClient = OkHttpClient.Builder()
        try {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC

            val httpCacheDirectory =
                File("/data/data/com.chintankhetiya.mvvm.architectural/cache", "offlineCache")
            //10 MB
            val cache = Cache(httpCacheDirectory, 10 * 1024 * 1024)

            httpClient = OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(30, TimeUnit.MINUTES)
                .writeTimeout(15, TimeUnit.MINUTES)
                .cache(cache)
                .addInterceptor(httpLoggingInterceptor)
        } catch (e: java.lang.Exception) {
            AppUtility.printLog(RetrofitService.javaClass.name, e.toString())
        }
        return httpClient
    }
}

