package com.example.fideltyproject.repository

import com.example.fideltyproject.network.RestApi

class MainRepository {

    private var client = RestApi.requestApi

    suspend fun getAnime() = client.getSearchAnime("naruto",1)
}