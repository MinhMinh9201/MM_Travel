package com.lqminhlab.mm_travel.src.resource.models

data class Photo(
    val caption: String? = "",
    val helpful_votes: String? = "",
    val id: String? = "",
    val images: ImagesX? = ImagesX(),
    val is_blessed: Boolean? = false,
    val published_date: String? = "",
    val uploaded_date: String? = "",
    val user: User? = User()
)