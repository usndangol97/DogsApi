package com.example.dogsapi.api

import com.example.dogsapi.data.ApiDataClass
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL ="https://api.thedogapi.com/v1"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface DogsApiService{
    @GET("breeds")
    suspend fun getInfoDogs(): ApiDataClass
}

object JokeApi{
    val retrofitService : DogsApiService by lazy {
        retrofit.create(DogsApiService::class.java)
    }
}