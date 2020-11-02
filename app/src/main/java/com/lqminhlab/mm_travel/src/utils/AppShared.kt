package com.lqminhlab.mm_travel.src.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.lqminhlab.mm_travel.App
import com.lqminhlab.mm_travel.src.resource.models.LocationModel

class AppShared(context: Context) {
    private val prefs: SharedPreferences =
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    private val gson: Gson = Gson()

    companion object {
        private const val PREFS_NAME = "Prefs_Travel"
        private const val KEY_RECOMMEND_LOCATION = "recommend_location"
        private const val KEY_EURO_LOCATION = "euro_location"
        private const val KEY_FEATURED_LOCATION = "featured_location"
        val prefs: AppShared by lazy {
            AppShared(App.instance)
        }
    }

    var recommendLocations: List<LocationModel>?
        get() {
            val jsonString = prefs.getString(KEY_RECOMMEND_LOCATION, null) ?: return null
            return gson.fromJson(jsonString, object : TypeToken<List<LocationModel>>() {}.type)
                ?: listOf<LocationModel>()
        }
        set(value) = prefs.edit { putString(KEY_RECOMMEND_LOCATION, gson.toJson(value)) }


    var euroLocations: List<LocationModel>?
        get() {
            val jsonString = prefs.getString(KEY_EURO_LOCATION, null) ?: return null
            return gson.fromJson(jsonString, object : TypeToken<List<LocationModel>>() {}.type)
                ?: listOf<LocationModel>()
        }
        set(value) = prefs.edit { putString(KEY_EURO_LOCATION, gson.toJson(value)) }


    var featuredLocations: List<LocationModel>?
        get() {
            val jsonString = prefs.getString(KEY_FEATURED_LOCATION, null) ?: return null
            return gson.fromJson(jsonString, object : TypeToken<List<LocationModel>>() {}.type)
                ?: listOf<LocationModel>()
        }
        set(value) = prefs.edit { putString(KEY_FEATURED_LOCATION, gson.toJson(value)) }
}