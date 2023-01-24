package com.urrr4545.weathertest.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.urrr4545.weathertest.R
import com.urrr4545.weathertest.databinding.FragmentMainBinding
import com.urrr4545.weathertest.presentation.adapter.SectionListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private val viewModel: MainViewModel by activityViewModels()
    private val sectionAdatper: SectionListAdapter by lazy {
        SectionListAdapter(arrayListOf(), viewModel)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        binding.vm = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initObserver()
    }

    private fun initView() {
        binding.rvWeatherSection.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = sectionAdatper

            ResourcesCompat.getDrawable(
                resources,
                R.drawable.item_divider_section,
                requireActivity().theme
            )?.run {
                val divider = DividerItemDecoration(context, LinearLayoutManager.VERTICAL)
                divider.setDrawable(this)
                this@apply.addItemDecoration(divider)
            }
        }
    }

    private fun initObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.weatherUiState.collectLatest { uiState ->
                    //todo ListAdapter diff 이슈로인하여 사용안함.. AsyncListDiffer로 대응하거나 설계를 다시해야함
                    sectionAdatper.updateDatas(uiState.itemList)
                }
            }
        }
    }
}