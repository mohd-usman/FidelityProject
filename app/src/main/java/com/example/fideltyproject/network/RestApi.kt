package com.example.fideltyproject.network

import androidx.lifecycle.MutableLiveData
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RestApi {

    private const val BASE_URL = "https://api.jikan.moe/v3/"

    private val retrofit=
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()


    val requestApi: ApiService = retrofit.create(ApiService::class.java)

}