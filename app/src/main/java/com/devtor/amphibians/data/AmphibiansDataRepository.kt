package com.devtor.amphibians.data

import com.devtor.amphibians.network.AmphibiansApiService
import com.devtor.amphibians.network.dto.AmphibiansItem

interface AmphibiansDataRepository {
    suspend fun getAmphibiansPhotos(): List<AmphibiansItem>
}

class NetworkAmphibiansDataRepository(
    val retrofitService: AmphibiansApiService
): AmphibiansDataRepository{
    override suspend fun getAmphibiansPhotos(): List<AmphibiansItem> = retrofitService.getDataAmphibians()
}