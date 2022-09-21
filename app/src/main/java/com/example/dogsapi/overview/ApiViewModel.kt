package com.example.dogsapi.overview

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dogsapi.api.DogsInfoApi
import com.example.dogsapi.data.ApiDataClass
import com.squareup.moshi.JsonDataException
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.log

class ApiViewModel: ViewModel() {
//    val xApiKey = "live_YDrP3rk8ukEVjAjVGS13jbOrpt8hUs0Jok4Ib9Xr5RR9qJl9JOJtjv6uJqUr46vx"
    private val _status = MutableLiveData<String>()

    // The external immutable LiveData for the request status
    val status: LiveData<String> = _status

    init {
        getInfoDogs()
    }

    private fun getInfoDogs() {
        viewModelScope.launch{
//            DogsInfoApi.retrofitService.getInfoDogs().enqueue(object: Callback<List<ApiDataClass>> {
//                override fun onResponse(
//                    call: Call<List<ApiDataClass>>,
//                    response: Response<List<ApiDataClass>>
//                ) {
//                    if (response.code() == 200) {
//                        _status.value = "Success: ${response.body()?.size ?: 0} Dogs Info retrieved"
//                    } else {
//                        _status.value = response.errorBody()?.toString() ?: "Something went wrong."
//                    }
//                }
//
//                override fun onFailure(call: Call<List<ApiDataClass>>, t: Throwable) {
//                    _status.value = t.message
//                }
//            })

            try {
                val listRes = DogsInfoApi.retrofitService.getInfoDogs()
                _status.value = "Success: ${listRes.size} Dogs Info retrieved"
            }catch (e: JsonDataException){
                Log.d(TAG, "getInfoDogs: ${e.toString()}")
                _status.value = "Json Data Exception"
            }catch (e: Exception){
                _status.value = e.message
            }
        }
    }

    companion object {
        private const val TAG ="ApiViewModel"
    }
}