package com.lqminhlab.mm_travel.src.resource.models

data class TaMessage(
    val is_red: Boolean? = false,
    val is_sensitive_issue: Boolean? = false,
    val owner_response: Any? = Any(),
    val text: String? = ""
)