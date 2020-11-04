package com.lqminhlab.mm_travel.src.resource.models

data class Hours(
    val timezone: String? = "",
    val week_ranges: List<List<WeekRange>>? = listOf()
)