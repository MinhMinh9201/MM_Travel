package com.lqminhlab.mm_travel.src.constants

class AppConstants {
    companion object {
        val baseURL by lazy { "https://tripadvisor1.p.rapidapi.com/" }
        val connectTimeout by lazy<Long> { 90 }
        val readTimeout by lazy<Long> { 60 }
        val writeTimeout by lazy<Long> { 90 }
        val baseHeader by lazy<Map<String, Any>> {
            mapOf<String, Any>(
                "x-rapidapi-host" to " tripadvisor1.p.rapidapi.com",
                "x-rapidapi-key" to "61c1d1f805msh731e462da81dd43p147c44jsnade9665f910f",
                "useQueryString" to "true"
            )
        }
    }
}