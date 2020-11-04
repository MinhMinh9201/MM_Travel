package com.lqminhlab.mm_travel.src.resource.models

data class Ancestor(
    val abbrv: Any? = Any(),
    val location_id: String? = "",
    val name: String? = "",
    val subcategory: List<Subcategory>? = listOf()
)