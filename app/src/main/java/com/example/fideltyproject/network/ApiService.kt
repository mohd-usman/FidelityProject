package com.example.fideltyproject.network

import com.example.fideltyproject.model.SearchAnimeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("search/anime")
    suspend fun getSearchAnime(
        @Query("q") keyword: String,
        @Query("page") page: Int
    ): Response<SearchAnimeResponse>
}