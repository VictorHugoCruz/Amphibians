package com.devtor.amphibians.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AmphibiansItem(
    @SerialName("description")
    val description: String,
    @SerialName("img_src")
    val imgSrc: String,
    @SerialName("name")
    val name: String,
    @SerialName("type")
    val type: String
)