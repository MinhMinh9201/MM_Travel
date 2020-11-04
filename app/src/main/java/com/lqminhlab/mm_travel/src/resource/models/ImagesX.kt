package com.lqminhlab.mm_travel.src.resource.models

data class ImagesX(
    val large: Large? = Large(),
    val medium: Medium? = Medium(),
    val original: Original? = Original(),
    val small: Small? = Small(),
    val thumbnail: Thumbnail? = Thumbnail()
)