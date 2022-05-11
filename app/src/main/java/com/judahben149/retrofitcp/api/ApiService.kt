package com.judahben149.retrofitcp.api

import com.judahben149.retrofitcp.data.CatJson
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("/facts/random")
    fun getCatFacts(): Call<CatJson>
}