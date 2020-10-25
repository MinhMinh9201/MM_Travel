package com.lqminhlab.mm_travel.src.resource.response

import com.google.gson.annotations.SerializedName

data class ResponseEntity<T> (
    @SerializedName("data") val data : T
)