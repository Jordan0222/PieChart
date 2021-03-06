package com.jordan.piechart

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import android.graphics.Color
import android.graphics.Typeface
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.*
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.viewinterop.AndroidView
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.jordan.piechart.ui.theme.*
import com.intuit.sdp.R as DP

@Composable
fun CrossFadePieChart() {
    Column(
        modifier = Modifier
            .padding(horizontal = dimensionResource(id = DP.dimen._8sdp))
            .padding(top = dimensionResource(id = DP.dimen._4sdp))
            .fillMaxWidth()
            .height(dimensionResource(id = DP.dimen._200sdp)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Crossfade(targetState = pieChartData) { chartData ->
            AndroidView(
                factory = { context ->
                    PieChart(context).apply {
                        layoutParams = LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT
                        )
                        initPieChart(this)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth(),
                update = {
                    updatePieChart(it, chartData)
                }
            )
        }
    }
}

fun initPieChart(chart: PieChart) {
    chart.description.isEnabled = false
    chart.isDrawHoleEnabled = false
    chart.setEntryLabelColor(Color.BLACK)
    chart.isRotationEnabled = false
    chart.setUsePercentValues(true)

    chart.setEntryLabelTextSize(14f)
    chart.setEntryLabelTypeface(Typeface.MONOSPACE);

    chart.legend.verticalAlignment = Legend.LegendVerticalAlignment.TOP
    chart.legend.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
    chart.legend.orientation = Legend.LegendOrientation.VERTICAL
    chart.legend.xEntrySpace = 7f
    chart.legend.yEntrySpace = 0f
    chart.legend.yOffset = 5f
    chart.legend.isEnabled = true
}

fun updatePieChart(
    chart: PieChart,
    data: List<PieChartItem>
) {
    val entries = ArrayList<PieEntry>()

    for (i in data.indices) {
        val item = data[i]
        entries.add(PieEntry(item.value ?: 0f, item.key ?: ""))
    }

    val ds = PieDataSet(entries, "")
    ds.colors = arrayListOf(
        Blue200.toArgb(),
        BlueGrey200.toArgb(),
        Blue300.toArgb(),
        BlueGrey300.toArgb(),
        Blue500.toArgb(),
        BlueGrey500.toArgb(),
        Blue700.toArgb(),
        BlueGrey700.toArgb()
    )

    ds.yValuePosition = PieDataSet.ValuePosition.INSIDE_SLICE
    ds.xValuePosition = PieDataSet.ValuePosition.OUTSIDE_SLICE

    ds.sliceSpace = 2f
    ds.valueTextColor = Color.BLACK
    ds.valueTextSize = 14f
    ds.valueFormatter = PercentFormatter(chart)

    val d = PieData(ds)
    chart.data = d
    chart.invalidate()
}