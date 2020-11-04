package com.lqminhlab.mm_travel.src.resource.models

data class Award(
    val award_type: String? = "",
    val categories: List<Any>? = listOf(),
    val display_name: String? = "",
    val images: Images? = Images(),
    val year: String? = ""
)