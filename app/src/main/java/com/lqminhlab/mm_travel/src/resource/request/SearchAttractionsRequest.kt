package com.lqminhlab.mm_travel.src.resource.request

data class SearchAttractionsRequest(
    val currency: String,
    val lang: String,
    val location_id: String,
    val unit: String,
    val sort: String
){
    fun toJSON(): Map<String, String?> =
        mapOf(
            "currency" to currency,
            "lang" to lang,
            "location_id" to location_id,
            "sort" to sort,
            "unit" to unit
        )
}