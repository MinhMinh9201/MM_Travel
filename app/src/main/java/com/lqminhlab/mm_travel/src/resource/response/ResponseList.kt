package com.lqminhlab.mm_travel.src.resource.response

import com.google.gson.annotations.SerializedName
import com.lqminhlab.mm_travel.src.resource.models.PagingResult

data class ResponseList<T> (
    @SerializedName("data") val data : List<T>,
    @SerializedName("metadata") val metadata : Any,
    @SerializedName("sort") val sort : List<Any>,
    @SerializedName("partial_content") val partialContent : Boolean,
    @SerializedName("tracking") val tracking : Any,
    @SerializedName("paging") val paging : PagingResult
)