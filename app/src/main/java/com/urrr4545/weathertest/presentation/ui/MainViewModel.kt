package com.urrr4545.weathertest.presentation.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.urrr4545.weathertest.domain.model.ListModel
import com.urrr4545.weathertest.domain.usecase.GetWeatherUseCase
import com.urrr4545.weathertest.domain.utils.OperationResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getWeatherUseCase: GetWeatherUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _weatherUiState = MutableStateFlow(WeatherUiState())
    val weatherUiState: StateFlow<WeatherUiState> = _weatherUiState.asStateFlow()

    init {
        initData()
    }

    private fun initData() {
        viewModelScope.launch {
            async {
                getWeatherList("Seoul", 0)
                getWeatherList("London", 1)
                getWeatherList("Chicago", 2)
            }
        }
    }

    private suspend fun getWeatherList(cityName: String, orderIndex: Int) {
        getWeatherUseCase.getGeoInfo(cityName)
            .onEach { dataState ->
                delay(1000)
                when (dataState) {
                    is OperationResult.Loading -> {
                        val itemList = _weatherUiState.value.initListModel(cityName, orderIndex)
                        _weatherUiState.update {
                            it.copy(
                                isLoading = true,
                                isErr = false,
                                itemList = itemList as MutableList<ListModel>
                            )
                        }
                    }
                    is OperationResult.Success -> {
                        dataState.data?.let {
                            val itemList = _weatherUiState.value.updateGeoInfo(orderIndex, it)
                            _weatherUiState.update {
                                it.copy(
                                    isLoading = false,
                                    isErr = false,
                                    itemList = itemList as MutableList<ListModel>
                                )
                            }
                        } ?: kotlin.run {
                            val itemList =
                                _weatherUiState.value.errorTargetListModel(cityName, orderIndex)
                            _weatherUiState.update {
                                it.copy(
                                    isLoading = false,
                                    isErr = true,
                                    itemList = itemList as MutableList<ListModel>,
                                    errorMessage = "err"
                                )
                            }
                        }
                    }
                    is OperationResult.Error -> {
                        val itemList =
                            _weatherUiState.value.errorTargetListModel(cityName, orderIndex)
                        _weatherUiState.update {
                            it.copy(
                                isLoading = false,
                                isErr = true,
                                itemList = itemList as MutableList<ListModel>,
                                errorMessage = dataState.message
                            )
                        }
                    }
                }
            }
            .filter { dataState ->
                when (dataState) {
                    is OperationResult.Success -> true
                    else -> false
                }
            }
            .flatMapConcat { dataState ->
                getWeatherUseCase.getWeatherInfo(dataState.data)
            }
            .collect { dataState ->
                when (dataState) {
                    is OperationResult.Loading -> {
                        val itemList = _weatherUiState.value.loadWeahterList(orderIndex)
                        _weatherUiState.update {
                            it.copy(
                                isLoading = true,
                                isErr = false,
                                itemList = itemList
                            )
                        }
                    }
                    is OperationResult.Success -> {
                        dataState.data?.let {
                            val itemList = _weatherUiState.value.successWeahterList(
                                orderIndex,
                                it.weathers.toMutableList()
                            )
                            _weatherUiState.update {
                                it.copy(
                                    isLoading = false,
                                    isErr = false,
                                    itemList = itemList
                                )
                            }
                        } ?: kotlin.run {
                            val itemList =
                                _weatherUiState.value.errorTargetListModel(cityName, orderIndex)
                            _weatherUiState.update {
                                it.copy(
                                    isLoading = false,
                                    isErr = true,
                                    itemList = itemList,
                                    errorMessage = "err weather"
                                )
                            }
                        }
                    }
                    is OperationResult.Error -> {
                        val itemList =
                            _weatherUiState.value.errorTargetListModel(cityName, orderIndex)
                        _weatherUiState.update {
                            it.copy(
                                isLoading = false,
                                isErr = true,
                                itemList = itemList,
                                errorMessage = dataState.message
                            )
                        }
                    }
                }
            }
    }
}