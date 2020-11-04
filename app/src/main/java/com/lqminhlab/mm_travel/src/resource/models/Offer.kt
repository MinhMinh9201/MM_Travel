package com.lqminhlab.mm_travel.src.resource.models

data class Offer(
    val description: Any? = Any(),
    val image_url: String? = "",
    val offer_type: String? = "",
    val partner: String? = "",
    val price: String? = "",
    val primary_category: String? = "",
    val product_code: String? = "",
    val rounded_up_price: String? = "",
    val title: String? = "",
    val url: String? = ""
)