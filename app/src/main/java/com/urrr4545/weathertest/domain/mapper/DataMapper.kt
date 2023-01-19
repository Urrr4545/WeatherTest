package com.urrr4545.weathertest.domain.mapper

import com.urrr4545.weathertest.data.dto.WeatherInfoDto
import com.urrr4545.weathertest.data.response.DailyResponse
import com.urrr4545.weathertest.domain.model.Weather
import com.urrr4545.weathertest.domain.model.WeatherInfo
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class DataMapper {

    fun mapToDomainModel(model: DailyResponse): WeatherInfo {
        return model.toWeatherInfo()
    }

    fun DailyResponse.toWeatherInfo() =
        WeatherInfo(
            city = this.city.name,
            weathers = this.list.map {
                it.toWeather()
            }
        )

    fun WeatherInfoDto.toWeather() =
        Weather(
            time = millisToDate(this.dt),
            min = tempToString(this.temp.min),
            max = tempToString(this.temp.max),
            main = this.weather.main,
            icon = this.weather.icon
        )

    private fun millisToDate(mills: Long): String {
        val targetDate = LocalDate.ofEpochDay(mills)
        val today = LocalDate.now()
        val tomorrow = today.plusDays(1)

        return if(today.equals(targetDate)) {
            "Today" //todo
        } else if(tomorrow.equals(targetDate)) {
            "Tomorrow"
        } else {
            val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("E LLL")
            "${targetDate.format(formatter)}"
        }
    }

    private fun tempToString(temp: Double) : String {
        return "${temp}℃"
    }
}