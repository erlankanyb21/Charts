package com.example.charts.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.charts.R
import com.example.charts.databinding.FragmentHomeBinding
import com.example.charts.ui.home.itemStats.ChartsFragment
import com.example.charts.ui.home.model.BusinessDto
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.tabs.TabLayoutMediator
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val binding by viewBinding(FragmentHomeBinding::bind)

    private val viewModel by viewModels<HomeViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listeners()
        observeStats()
    }

    private fun observeStats() {
        viewModel.getStats().observe(viewLifecycleOwner) { businessDto ->
            dataSet(businessDto)
            binding.txtCount.text = "Последние ${businessDto.viewCount.size} дней"
            binding.txtReset.isVisible = false
        }
    }

    private fun listeners() {
        binding.picker.setOnClickListener {
            setupdatepicker()
        }
        binding.txtReset.setOnClickListener {
            observeStats()
            binding.txtCount.text = ""
            binding.txtReset.isVisible = false
        }
    }

    private fun dataSet(businessDto: BusinessDto) {
        val fragmentList = listOf(
            businessDto.viewCount.map { it.count to it.date }.unzip(),
            businessDto.callCount.map { it.count to it.date }.unzip(),
            businessDto.messageCount.map { it.count to it.date }.unzip(),
            businessDto.clickCount.map { it.count to it.date }.unzip()
        ).map { (counts, dates) -> ChartsFragment.newInstance(counts, dates) }

        val adapter = FragmentViewPagerAdapter(parentFragmentManager, lifecycle, fragmentList)
        binding.viewPager.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Views"
                1 -> tab.text = "Calls"
                2 -> tab.text = "Message"
                3 -> tab.text = "Click"
            }
        }.attach()
    }

    private fun setupdatepicker() {
        val builder = MaterialDatePicker.Builder.dateRangePicker()
        builder.setTitleText("Выберите период")
        builder.setTheme(R.style.CustomDatePickerCalendarStyle)
        val picker = builder.build()

        picker.show(parentFragmentManager, picker.toString())
        picker.addOnPositiveButtonClickListener { pair ->
            viewModel.getStats(
                (pair.first / 1000L).toString(), (pair.second / 1000L).toString()
            ).observe(viewLifecycleOwner) { businessDto ->
                dataSet(businessDto)
                val start = convertMillisecondsToDate(pair.first, "dd MMMM")
                val end = convertMillisecondsToDate(pair.second, "dd MMMM")

                when {
                    binding.txtCount.text.isEmpty() -> {
                        binding.txtReset.isVisible = false
                    }
                    else -> {
                        binding.txtCount.text = "C ${start} - ${end}"
                        binding.txtReset.isVisible = true
                    }
                }
            }
        }
    }

    @SuppressLint("NewApi")
    fun convertMillisecondsToDate(milliseconds: Long, dateFormat: String): String {
        val instant = Instant.ofEpochMilli(milliseconds)
        val dateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
        val formatter = DateTimeFormatter.ofPattern(dateFormat, Locale.getDefault())
        return dateTime.format(formatter)
    }
}