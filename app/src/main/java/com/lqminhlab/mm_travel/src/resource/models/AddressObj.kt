package com.lqminhlab.mm_travel.src.resource.models

data class AddressObj(
    val city: String? = "",
    val country: String? = "",
    val postalcode: String? = "",
    val state: Any? = Any(),
    val street1: String? = "",
    val street2: String? = ""
)