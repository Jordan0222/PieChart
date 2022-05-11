package com.jordan.piechart

data class PieChartItem(
    val key: String?,
    val value: Float?
)

val pieChartData = listOf(
    PieChartItem(
        "Java",
        30.0f
    ),
    PieChartItem(
        "Kotlin",
        35.0f
    ),
    PieChartItem(
        "Python",
        35.0f
    )
)
