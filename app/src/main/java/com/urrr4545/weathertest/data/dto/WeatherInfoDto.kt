package com.urrr4545.weathertest.data.dto

import com.google.gson.annotations.SerializedName

class WeatherInfoDto(
    @SerializedName("dt") val dt: Long, //데이터 예측 시간
    @SerializedName("temp") val temp: TempDto,
    @SerializedName("list") val weather: WeatherDto
    //..일단 필요한데이터들위주로
)

class TempDto(
    @SerializedName("day") val day: Double, //일온도
    @SerializedName("min") val min: Double, //최소 일일온도
    @SerializedName("max") val max: Double, //최대 일일온도
)

class WeatherDto(
    @SerializedName("id") val id: Int, //날씨조건 id
    @SerializedName("main") val main: String, //날씨 매개변수 그룹(비, 눈 등)
    @SerializedName("description") val description: String,
    @SerializedName("icon") val icon: String //아이콘id
)