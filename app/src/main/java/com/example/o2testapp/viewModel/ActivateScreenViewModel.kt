package com.example.o2testapp.viewModel

import androidx.lifecycle.ViewModel
import com.example.o2testapp.networking.RestApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

class ActivateScreenViewModel: ViewModel() {
    val okHttpClient = OkHttpClient.Builder().build()

    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.o2.sk")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun callO2(code: String){
        val restApi = retrofit.create(RestApi::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = restApi.getO2Response(code = code)
                if (response.isSuccessful){
                    println("succesfull")
                } else {
                    println(response.message())
                }
            } catch (e: Exception){
                println(e)
            }
        }
    }
}