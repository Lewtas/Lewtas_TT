package com.example.lewtastt.api

import com.example.lewtastt.api.model.ApiRequestTemp
import com.example.lewtastt.api.model.ApiResponseTemp
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit
import retrofit2.create
import retrofit2.http.Body
import retrofit2.http.POST

// all from https://api.random.org/json-rpc/4/basic
internal interface ApiService {

    @POST("/json-rpc/4/invoke")
    suspend fun request(@Body request: ApiRequestTemp): ApiResponseTemp

    companion object {
        fun create(): ApiService = retrofit.create()
    }
}

private val retrofit by lazy {
    Retrofit.Builder()
        .baseUrl("https://api.random.org")
        .addConverterFactory(Json.asConverterFactory(MediaType.get("application/json")))
        .build()
}