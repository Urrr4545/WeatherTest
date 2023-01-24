package com.urrr4545.weathertest.data.dto

import com.google.gson.annotations.SerializedName

class GeoInfoDto(
    @SerializedName("name") val name: String,
    @SerializedName("lat") val lat: Double,
    @SerializedName("lon") val lon: Double,
    @SerializedName("country") val country: String
)
