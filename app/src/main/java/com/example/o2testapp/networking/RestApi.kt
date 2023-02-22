package com.example.o2testapp.networking

import com.google.gson.internal.LinkedTreeMap
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RestApi {

    @GET("/version")
    suspend fun getO2Response(@Query("code") code: String): Response<LinkedTreeMap<String, String>>

}