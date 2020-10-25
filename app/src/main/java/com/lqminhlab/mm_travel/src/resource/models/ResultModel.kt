package com.lqminhlab.mm_travel.src.resource.models

import com.google.gson.annotations.SerializedName

data class ResultModel<T> (
    @SerializedName("result_type") val type : String,
    @SerializedName("result_object") val data : T,
    @SerializedName("scope") val scope : String,
    @SerializedName("is_top_result") val isTop : Boolean
)