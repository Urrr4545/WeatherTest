package com.urrr4545.weathertest.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.urrr4545.weathertest.R
import com.urrr4545.weathertest.domain.model.Weather
import com.urrr4545.weathertest.presentation.ui.MainViewModel
import com.urrr4545.weathertest.presentation.viewholder.WeatherViewHolder

class WeatherListAdapter(
    private val weatherList: ArrayList<Weather>,
    private val mainViewModel: MainViewModel
) : RecyclerView.Adapter<WeatherViewHolder>() {

    override fun getItemCount(): Int {
        return weatherList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        return WeatherViewHolder(
            parent.context,
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_weather_info,
                parent,
                false
            ),
            mainViewModel
        )
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.bind(weatherList.get(position))
    }

    fun updateDatas(list: List<Weather>) {
        weatherList.clear()
        weatherList.addAll(list)
        notifyDataSetChanged()
    }
}