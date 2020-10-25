package com.lqminhlab.mm_travel.src.resource.request

data class SearchAttractions(
    val currency: String,
    val lang: String,
    val location_id: String,
    val lunit: String,
    val sort: String
)