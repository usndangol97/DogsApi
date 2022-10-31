package com.example.dogsapi.api

import android.app.Application
import android.content.Context
import com.example.dogsapi.MyApplication
import com.example.dogsapi.NetworkUtils
import com.example.dogsapi.data.ApiDataClass
import com.paoneking.nepallipi_typenewa.rest.Interceptors
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL ="https://api.thedogapi.com/"
//private const val BASE_URL = "https://www.boredapi.com/"


fun httpCache(context: Context): Cache {
    val cacheSize = (5 * 1024 * 1024).toLong()
    return Cache(context.applicationContext.cacheDir, cacheSize)
}

//val cacheSize = (5 x 1024 x 1024).toLong()

/*val okHttpClient = OkHttpClient.Builder()
    .cache(httpCache(MyApplication.getInstance()))
    // Add an Interceptor to the OkHttpClient.
    .addInterceptor { chain ->

        // Get the request from the chain.
        var request = chain.request()
        request = if (!NetworkUtils.hasNetwork())
            request.newBuilder().header("Cache-Control", "public, max-age=" + 5).build()
        else
            request.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7).build()

        chain.proceed(request)
    }
    .addNetworkInterceptor(Interceptors.ResponseCacheInterceptor())
    .addInterceptor(Interceptors.OfflineResponseCacheInterceptor(MyApplication.getInstance()))
    .build()*/

val okHttpClient = OkHttpClient.Builder()
    .cache(httpCache(MyApplication.getInstance()))
    // Add an Interceptor to the OkHttpClient.
    .addNetworkInterceptor(Interceptors.ResponseCacheInterceptor())
    .addInterceptor(Interceptors.OfflineResponseCacheInterceptor(MyApplication.getInstance()))
    .build()

/*val okHttpClient = OkHttpClient.Builder()
    .cache(httpCache(MyApplication.getInstance()))
    // Add an Interceptor to the OkHttpClient.
    .addNetworkInterceptor(Interceptors.CacheInterceptor())
    .addInterceptor(Interceptors.ForceCacheInterceptor())
    .build()*/

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .client(okHttpClient)
    .build()

interface DogsServiceApi{
    @GET("v1/breeds")
    suspend fun getInfoDogs(): List<ApiDataClass>
}

object DogsInfoApi{
    val retrofitService : DogsServiceApi by lazy {
        retrofit.create(DogsServiceApi::class.java)
    }
}