package com.urrr4545.weathertest.data.dto

import com.google.gson.annotations.SerializedName

class CityDto(
    @SerializedName("id") val id: Int, //도시id
    @SerializedName("name") val name: String, //도시이름
    @SerializedName("coord") val coord: CoordDto,
    @SerializedName("country") val country: String, //국가코드
    @SerializedName("timezone") val timezone: Int //UTC에서 초단위로 이동
)

class CoordDto(
    @SerializedName("lon") val lon: Double, //위도
    @SerializedName("lat") val lat: Double //경도
)