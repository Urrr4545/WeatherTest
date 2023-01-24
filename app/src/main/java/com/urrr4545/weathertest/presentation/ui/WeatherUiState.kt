package com.urrr4545.weathertest.presentation.ui

import com.urrr4545.weathertest.domain.model.GeoInfo
import com.urrr4545.weathertest.domain.model.ListModel
import com.urrr4545.weathertest.domain.model.Weather

data class WeatherUiState(
    val isLoading: Boolean = true,
    val isErr: Boolean = false,
    var itemList: List<ListModel> = emptyList(),
    val errorMessage: String? = null
) {
    fun initListModel(city: String, orderIndex: Int): List<ListModel> {
        val findItem = itemList.find { it.orderIndex == orderIndex }
        val index = itemList.indexOf(findItem)
        val copyItem = itemList.toMutableList()

        val item = ListModel().initModel(city, orderIndex)
        if (index == -1) {
            copyItem.add(item)
        } else {
            copyItem[index] = item
        }

        return copyItem.sortedBy { it.orderIndex }
    }

    fun updateGeoInfo(orderIndex: Int, geoInfo: GeoInfo): List<ListModel> {
        val findItem = itemList.find { it.orderIndex == orderIndex }
        val index = itemList.indexOf(findItem)
        val copyItem = itemList.toMutableList()

        findItem?.let {
            it.geoInfo = geoInfo
            copyItem[index] = it
            return copyItem
        } ?: kotlin.run {
            return errorTargetListModel(geoInfo.name, orderIndex)
        }
    }

    fun errorTargetListModel(city: String, orderIndex: Int): List<ListModel> {
        val findItem = itemList.find { it.orderIndex == orderIndex }
        val index = itemList.indexOf(findItem)
        val copyItem = itemList.toMutableList()

        val item = ListModel().errorModel(city, orderIndex)
        if (index == -1) {
            copyItem.add(item)
        } else {
            copyItem[index] = item
        }

        return copyItem.sortedBy { it.orderIndex }
    }

    fun loadWeahterList(orderIndex: Int): List<ListModel> {
        val findItem = itemList.find { it.orderIndex == orderIndex }
        val index = itemList.indexOf(findItem)
        val copyItem = itemList.toMutableList()

        findItem?.let {
            it.isErr = false
            it.isLoading = true
            it.weathers = emptyList()
            copyItem[index] = it
            return copyItem
        }
        return copyItem
    }

    fun successWeahterList(orderIndex: Int, weathers: MutableList<Weather>): List<ListModel> {
        val findItem = itemList.find { it.orderIndex == orderIndex }
        val index = itemList.indexOf(findItem)
        val copyItem = itemList.toMutableList()

        findItem?.let {
            it.isErr = false
            it.isLoading = false
            var findWeatherList = it.weathers.toMutableList()
            findWeatherList.clear()
            it.weathers = weathers
            copyItem[index] = it
            return copyItem
        }
        return copyItem
    }
}