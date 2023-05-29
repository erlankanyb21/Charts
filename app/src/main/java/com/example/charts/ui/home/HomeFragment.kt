package com.example.charts.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.charts.R
import com.example.charts.databinding.FragmentHomeBinding
import com.example.charts.ui.home.base.BaseFragment
import com.example.charts.ui.home.itemStats.ChartsFragment
import com.example.charts.ui.home.model.BusinessDto
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.tabs.TabLayoutMediator
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(R.layout.fragment_home) {

    override val binding by viewBinding(FragmentHomeBinding::bind)

    override val viewModel by viewModels<HomeViewModel>()

    override fun initialize() {
        setupViewPager()
    }

    override fun constructListeners() {
        binding.picker.setOnClickListener {
            setupdatepicker()
        }
        binding.txtReset.setOnClickListener {
            binding.txtCount.text = ""
            binding.txtReset.isVisible = false
        }
    }

    override fun establishRequest() {
        binding.txtReset.setOnClickListener {
            Toast.makeText(requireContext(), "${it}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupViewPager() {

        val dataForTab1 = listOf(1, 2, 3)
        val dataForTab2 = listOf(4, 5, 6)
        val dataForTab3 = listOf(7, 8, 9)
        val dataForTab4 = listOf(7, 8, 9)

        val fragmentList = listOf(
            ChartsFragment.newInstance(dataForTab1, listOf("Calls")),
            ChartsFragment.newInstance(dataForTab2, listOf("Views")),
            ChartsFragment.newInstance(dataForTab3, listOf("Clicks")),
            ChartsFragment.newInstance(dataForTab4, listOf("Message")),
        )

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

            val start = convertMillisecondsToDate(pair.first, "dd MMMM")
            val end = convertMillisecondsToDate(pair.second, "dd MMMM")

            when {
                binding.txtCount.text.isEmpty() -> {
                    binding.txtReset.isVisible = false
                }

                else -> {
                    binding.txtCount.text = "C ${start} - ${end}"
                    binding.txtReset.setOnClickListener {
                        Toast.makeText(requireContext(), "jopa", Toast.LENGTH_SHORT).show()
                    }
                    binding.txtReset.isVisible = true
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