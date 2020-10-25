package com.lqminhlab.mm_travel.src.resource.models

data class LocationModel(
    val ancestors: List<Any>,
    val awards: List<Any>,
    val category: Any,
    val category_counts: Any,
    val description: String,
    val doubleclick_zone: String,
    val geo_description: String,
    val geo_type: String,
    val has_attraction_coverpage: Boolean,
    val has_curated_shopping_list: Boolean,
    val has_restaurant_coverpage: Boolean,
    val is_jfy_enabled: Boolean,
    val latitude: String,
    val location_id: String,
    val location_string: String,
    val longitude: String,
    val name: String,
    val nearby_attractions: List<Any>,
    val nearest_metro_station: List<Any>,
    val num_reviews: String,
    val photo: PhotoModel,
    val preferred_map_engine: String,
    val subcategory: List<Any>,
    val timezone: String,
    val web_url: String
)