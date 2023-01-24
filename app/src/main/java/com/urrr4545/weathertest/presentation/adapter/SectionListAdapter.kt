package com.urrr4545.weathertest.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.urrr4545.weathertest.R
import com.urrr4545.weathertest.domain.model.ListModel
import com.urrr4545.weathertest.presentation.ui.MainViewModel
import com.urrr4545.weathertest.presentation.viewholder.SectionViewHolder

class SectionListAdapter(
    private val sectionList: ArrayList<ListModel>,
    private val mainViewModel: MainViewModel
) : RecyclerView.Adapter<SectionViewHolder>() {

    override fun getItemCount(): Int {
        return sectionList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SectionViewHolder {
        return SectionViewHolder(
            parent.context,
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_section_info,
                parent,
                false
            ),
            mainViewModel
        )
    }

    override fun onBindViewHolder(holder: SectionViewHolder, position: Int) {
        holder.bind(sectionList.get(position))
    }

    fun updateDatas(list: List<ListModel>) {
        sectionList.clear()
        sectionList.addAll(list)
        notifyDataSetChanged()
    }
}

