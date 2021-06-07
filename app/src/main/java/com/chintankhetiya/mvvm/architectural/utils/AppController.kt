package com.chintankhetiya.mvvm.architectural.utils

import android.annotation.SuppressLint
import android.content.Context
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.chintankhetiya.mvvm.architectural.webservice.APIConstant
import okhttp3.Cache

class AppController : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        sAppDataManager =
            AppController()
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
        myCache = Cache(this.cacheDir, APIConstant.APP_CACHE_SIZE)
    }

    companion object {
        lateinit var myCache: Cache

        @SuppressLint("StaticFieldLeak")
        lateinit var sAppDataManager: AppController

        @get:Synchronized
        val appInstance: AppController
            get() {
                sAppDataManager =
                    AppController()
                return sAppDataManager
            }
    }
}