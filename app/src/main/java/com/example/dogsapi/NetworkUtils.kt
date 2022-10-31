package com.example.dogsapi

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.appcompat.app.AppCompatActivity
import com.example.dogsapi.MyApplication


object NetworkUtils {
    fun hasNetwork(): Boolean {
        var isConnected = false // Initial Value
        val connectivityManager = MyApplication.getInstance().getSystemService(AppCompatActivity.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        if (activeNetwork != null && activeNetwork.isConnected)
            isConnected = true
        return isConnected
    }
}

fun Context.hasNetwork(): Boolean {
    var isConnected = false // Initial Value
    val connectivityManager = this.getSystemService(AppCompatActivity.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
    if (activeNetwork != null && activeNetwork.isConnected)
        isConnected = true
    return isConnected
}
