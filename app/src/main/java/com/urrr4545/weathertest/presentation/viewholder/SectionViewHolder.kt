package com.urrr4545.weathertest.presentation.viewholder

import android.content.Context
import android.view.View
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.urrr4545.weathertest.R
import com.urrr4545.weathertest.databinding.ItemSectionInfoBinding
import com.urrr4545.weathertest.domain.model.ListModel
import com.urrr4545.weathertest.presentation.adapter.WeatherListAdapter
import com.urrr4545.weathertest.presentation.adapter.setItemLoading
import com.urrr4545.weathertest.presentation.ui.MainViewModel

class SectionViewHolder(
    private val context: Context,
    private val binding: ItemSectionInfoBinding,
    private val mainViewModel: MainViewModel
) : RecyclerView.ViewHolder(binding.root) {

    private val weatherListAdapter: WeatherListAdapter by lazy {
        WeatherListAdapter(arrayListOf(), mainViewModel)
    }

    fun bind(item: ListModel) {
        binding.apply {
            this.item = item
            this.vm = mainViewModel
        }

        binding.rvWeather.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = weatherListAdapter

            ResourcesCompat.getDrawable(
                resources,
                R.drawable.item_divider_weather,
                itemView.context.theme
            )?.run {
                val divider = DividerItemDecoration(context, LinearLayoutManager.VERTICAL)
                divider.setDrawable(this)
                this@apply.addItemDecoration(divider)
            }
        }
        weatherListAdapter.updateDatas(item.weathers)

        if (item.isLoading) {
            binding.shimmerLayout.visibility = View.VISIBLE
            binding.shimmerLayout.startShimmer()
        } else {
            binding.shimmerLayout.visibility = View.GONE
            binding.shimmerLayout.stopShimmer()
        }
    }
}