package com.judahben149.retrofitcp

import com.judahben149.retrofitcp.api.CatJson
import retrofit2.Call
import retrofit2.http.GET

interface ApiRequests {

    @GET("/facts/random")
    fun getCatFacts(): Call<CatJson>
}