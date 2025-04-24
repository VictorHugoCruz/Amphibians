package com.devtor.amphibians.network

import com.devtor.amphibians.network.dto.AmphibiansItem
import retrofit2.http.GET

interface AmphibiansApiService {
    @GET("amphibians/")
    suspend fun getDataAmphibians(): List<AmphibiansItem>
}