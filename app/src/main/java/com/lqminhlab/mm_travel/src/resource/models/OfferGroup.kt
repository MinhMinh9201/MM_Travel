package com.lqminhlab.mm_travel.src.resource.models

data class OfferGroup(
    val has_see_all_url: Boolean? = false,
    val is_eligible_for_ap_list: Boolean? = false,
    val lowest_price: String? = "",
    val offer_list: List<Offer>? = listOf()
)