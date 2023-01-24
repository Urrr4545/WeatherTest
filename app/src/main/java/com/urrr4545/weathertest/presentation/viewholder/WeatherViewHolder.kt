package com.urrr4545.weathertest.presentation.viewholder

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.urrr4545.weathertest.databinding.ItemWeatherInfoBinding
import com.urrr4545.weathertest.domain.model.ListModel
import com.urrr4545.weathertest.domain.model.Weather
import com.urrr4545.weathertest.presentation.ui.MainViewModel

class WeatherViewHolder(
    private val context: Context,
    private val binding: ItemWeatherInfoBinding,
    private val mainViewModel: MainViewModel
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Weather) {
        binding.apply {
            this.item = item
        }
    }
}