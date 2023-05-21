package com.example.charts.ui.home.itemStats

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.charts.R
import com.example.charts.databinding.FragmentChartsBinding
import com.example.charts.ui.home.HomeViewModel
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartAlignType
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartModel
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartType
import com.github.aachartmodel.aainfographics.aachartcreator.AASeriesElement
import com.github.aachartmodel.aainfographics.aaoptionsmodel.AAScrollablePlotArea
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

class ChartsFragment : Fragment(R.layout.fragment_charts) {

    private val binding by viewBinding(FragmentChartsBinding::bind)

    private val viewModel by viewModels<HomeViewModel>()

    companion object {
        private const val ARG_Bar = "data"
        private const val ARG_Date = "date"

        fun newInstance(data: List<Int>, date: List<String>): ChartsFragment {
            val fragment = ChartsFragment()
            val args = Bundle()
            args.putIntArray(ARG_Bar, data.toIntArray())
            args.putStringArray(ARG_Date, date.toTypedArray())
            fragment.arguments = args
            return fragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getStats().observe(viewLifecycleOwner) {
            val bars = arguments?.getIntArray(ARG_Bar)?.map { it }

            val date = arguments?.getStringArray(ARG_Date)?.map { convertDateFormat(it) }

            setUpCharts(bars, date)
        }
    }

    private fun setUpCharts(data: List<Int>?, date: List<String>?, rangeTitle:String? = null) {
        val seriesElement =
            AASeriesElement().name("").showInLegend(false).data(data!!.toTypedArray())

        val superiority = calculateSuperiority(data)

        val maxSuperiority = superiority.maxOrNull() ?: 0f

        val dataWithColors = data.mapIndexed { index, value ->
            val opacity = (superiority[index] / maxSuperiority) * 0.5f + 0.5f
            val color = "rgba(29, 115, 182, $opacity)"
            mapOf("y" to value, "color" to color)
        }

        seriesElement.data(dataWithColors.toTypedArray())

        val chartModel1 =
            AAChartModel().chartType(AAChartType.Bar).yAxisVisible(false).xAxisVisible(true)
                .categories(date!!.toTypedArray().reversedArray()).touchEventEnabled(true)
                .dataLabelsEnabled(true).yAxisAllowDecimals(true).xAxisTickInterval(0)
                .dataLabelsEnabled(true).tooltipEnabled(false)
                .scrollablePlotArea(AAScrollablePlotArea()).subtitleAlign(AAChartAlignType.Left)
                .series(arrayOf(seriesElement))

        binding.charts.aa_drawChartWithChartModel(chartModel1)
    }

    private fun calculateSuperiority(data: List<Int>): List<Float> {
        val maxVal = data.maxOrNull()?.toFloat() ?: 0f

        return data.map { value ->
            (value.toFloat() / maxVal) * 100
        }
    }

    @SuppressLint("NewApi")
    fun convertDateFormat(inputDate: String): String {
        val inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.getDefault())
        val outputFormat = DateTimeFormatter.ofPattern("dd MMM", Locale.getDefault())

        val date = LocalDate.parse(inputDate, inputFormat)
        return outputFormat.format(date)
    }
}