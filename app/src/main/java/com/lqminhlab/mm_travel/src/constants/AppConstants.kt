package com.lqminhlab.mm_travel.src.constants

import com.lqminhlab.mm_travel.src.resource.request.SearchLocationRequest

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

        //Featured:
        val featuredCities: ArrayList<SearchLocationRequest> = arrayListOf(
            SearchLocationRequest("Budapest"),
            SearchLocationRequest("Edinburgh"),
            SearchLocationRequest("Innsbruck"),
            SearchLocationRequest("Bergen"),
            SearchLocationRequest("Paris"),
            SearchLocationRequest("Vienna"),
            SearchLocationRequest("York"),
            SearchLocationRequest("Copenhagen")
        )

        //Euro:
        val euroCities: ArrayList<SearchLocationRequest> = arrayListOf(
            SearchLocationRequest("Prague"),
            SearchLocationRequest("Porto"),
            SearchLocationRequest("Bern"),
            SearchLocationRequest("Athens"),
            SearchLocationRequest("Florence"),
            SearchLocationRequest("Bruges"),
        )

        //Recommend:
        val recommendCities: ArrayList<SearchLocationRequest> = arrayListOf(
            SearchLocationRequest("Fuji"),
            SearchLocationRequest("Ha long"),
            SearchLocationRequest("SEORAK"),
            SearchLocationRequest("Manpupuner"),
            SearchLocationRequest("Mont Saint-Michel"),
            SearchLocationRequest("Sapa"),
        )
    }
}