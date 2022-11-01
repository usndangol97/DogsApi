package com.example.dogsapi.network

import android.app.Application

class MyApplication : Application(){
    override fun onCreate() {
        instance = this
        super.onCreate()
        if(instance == null){
            instance = this
        }
    }

    companion object {
        private lateinit var instance: MyApplication

        fun getInstance(): MyApplication {
            return instance
        }
    }
}