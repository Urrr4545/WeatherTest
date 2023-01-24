package com.urrr4545.weathertest.data.dto

import com.google.gson.annotations.SerializedName

class WeatherInfoDto(
    @SerializedName("dt") val dt: Long, //데이터 예측 시간
    @SerializedName("main") val main: TempDto,
    @SerializedName("weather") val weather: List<WeatherDto>,
    @SerializedName("dt_txt") val dtTxt: String,


    //..일단 필요한데이터들위주로
)

class TempDto(
    @SerializedName("temp") val temp: Double, //일온도
    @SerializedName("temp_min") val min: Double, //최소 일일온도
    @SerializedName("temp_max") val max: Double, //최대 일일온도
)

class WeatherDto(
    @SerializedName("id") val id: Int, //날씨조건 id
    @SerializedName("main") val main: String, //날씨 매개변수 그룹(비, 눈 등)
    @SerializedName("description") val description: String,
    @SerializedName("icon") val icon: String //아이콘id
)