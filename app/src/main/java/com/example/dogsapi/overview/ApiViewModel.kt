package com.example.dogsapi.overview

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dogsapi.api.DogsInfoApi
import com.example.dogsapi.data.ApiDataClass
import kotlinx.coroutines.launch
import kotlin.math.log

class ApiViewModel: ViewModel() {
//    val xApiKey = "live_YDrP3rk8ukEVjAjVGS13jbOrpt8hUs0Jok4Ib9Xr5RR9qJl9JOJtjv6uJqUr46vx"
    private val _status = MutableLiveData<ApiDataClass>()

    // The external immutable LiveData for the request status
    val status: LiveData<ApiDataClass> = _status

    init {
        getInfoDogs()
    }

    private fun getInfoDogs() {
        viewModelScope.launch{
            try {
                val listRes = DogsInfoApi.retrofitService.getInfoDogs()
                Log.d("Result","@{listRes}")
                _status.value = listRes
            }catch (e: Exception){
//                _status.value = e.message
            }
        }
    }
}