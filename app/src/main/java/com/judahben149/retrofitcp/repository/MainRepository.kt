package com.judahben149.retrofitcp.repository

import com.judahben149.retrofitcp.api.ApiClient
import com.judahben149.retrofitcp.data.CatJson
import retrofit2.Response

class MainRepository {

    suspend fun getCatFacts(): Response<CatJson> {
        return ApiClient.apiService.getCatFacts()
    }
}