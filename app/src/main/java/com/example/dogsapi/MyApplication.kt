package com.example.dogsapi

import android.app.Application
import android.net.ConnectivityManager

class MyApplication : Application(){
    override fun onCreate() {
        instance = this
        super.onCreate()
        if(instance == null){
            instance = this
        }
    }

    fun hasNetwork(): Boolean {
        return instance.isNetworkConnected()
    }

    private fun isNetworkConnected(): Boolean {
        val cm = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting
    }

    companion object {
        private lateinit var instance: MyApplication

        fun getInstance(): MyApplication {
            return instance
        }
    }
}
