package com.lqminhlab.mm_travel.src.resource.request

data class SearchLocationRequest(
    val query: String,
    val currency: String? = "",
    val lang: String? = "",
    val limit: String? = "",
    val location_id: String? = "",
    val offset: String? = "",
    val sort: String? = "",
    val units: String? = ""
) {
    fun toJSON(): Map<String, String?> =
        mapOf(
            "query" to query,
            "currency" to currency,
            "lang" to lang,
            "limit" to limit,
            "location_id" to location_id,
            "offset" to offset,
            "sort" to sort,
            "units" to units
        )
}