package com.urrr4545.weathertest.domain.model

data class ListModel(
    var cityName: String = "",
    var orderIndex: Int = -1,
    var isLoading: Boolean = false,
    var isErr: Boolean = false,
    var geoInfo: GeoInfo? = null,
    var weathers: List<Weather> = emptyList()
) {
    fun initModel(city: String, orderIndex: Int) =
        ListModel(
            cityName = city,
            isLoading = true,
            isErr = false,
            orderIndex = orderIndex
        )

    fun errorModel(city: String, orderIndex: Int) =
        ListModel(
            cityName = city,
            isLoading = false,
            isErr = true,
            orderIndex = orderIndex
        )
}