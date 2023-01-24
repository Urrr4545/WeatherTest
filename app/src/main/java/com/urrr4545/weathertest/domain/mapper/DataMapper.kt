package com.urrr4545.weathertest.domain.mapper

import com.urrr4545.weathertest.data.dto.GeoInfoDto
import com.urrr4545.weathertest.data.dto.WeatherDto
import com.urrr4545.weathertest.data.dto.WeatherInfoDto
import com.urrr4545.weathertest.data.response.DailyResponse
import com.urrr4545.weathertest.domain.model.GeoInfo
import com.urrr4545.weathertest.domain.model.Weather
import com.urrr4545.weathertest.domain.model.WeatherInfo
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*
import javax.inject.Inject


class DataMapper {

    fun mapToWeatherModel(model: DailyResponse): WeatherInfo {
        return model.toWeatherInfo()
    }

    fun mapToGeoModel(model: List<GeoInfoDto>): GeoInfo? {
        return model.toGeoInfo()
    }

    fun DailyResponse.toWeatherInfo(): WeatherInfo {
        return WeatherInfo(
            weathers = this.list.filterIndexed { index, _ -> index % 8 == 0 }.sortedBy { it.dt }
                .map {
                    it.toWeather(this.city.name)
                }
        )
    }


    fun WeatherInfoDto.toWeather(city: String): Weather {
        var firstItem = this.weather.toFristItem()
        return Weather(
            id = firstItem?.id ?: -1,
            date = millisToDate(this.dt),
            dateStr = millisToDateString(this.dt),
            min = tempToString(this.main.min),
            max = tempToString(this.main.max),
            main = firstItem?.main ?: "none",
            icon = firstItem?.icon ?: "none",
            cityName = city
        )
    }

    fun List<WeatherDto>.toFristItem(): WeatherDto? {
        this.firstOrNull().run {
            this?.let {
                return it
            }
        }
        return null
    }

    fun List<GeoInfoDto>.toGeoInfo(): GeoInfo? {
        this.firstOrNull().run {
            this?.let {
                return it.toGeoInfo()
            }
        }
        return null
    }

    fun GeoInfoDto.toGeoInfo() =
        GeoInfo(
            name = this.name,
            lat = this.lat,
            lon = this.lon,
            country = this.country
        )

    private fun millisToDate(dt: Long): String {

        val date: LocalDate =
            Instant.ofEpochMilli(dt * 1000).atZone(ZoneId.systemDefault()).toLocalDate()
        return "${date}"
    }

    private fun millisToDateString(dt: Long): String {

        val date: LocalDate =
            Instant.ofEpochMilli(dt * 1000).atZone(ZoneId.systemDefault()).toLocalDate()
        val today = LocalDate.now()
        val tomorrow = today.plusDays(1)
        return if (today.equals(date)) {
            "Today" //todo
        } else if (tomorrow.equals(date)) {
            "Tomorrow"
        } else {
            val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("E LLL")
            "${date.format(formatter)}"
        }
    }

    private fun tempToString(temp: Double): String {
        return "${temp}℃"
    }
}