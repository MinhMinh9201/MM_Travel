package com.lqminhlab.mm_travel.src.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lqminhlab.mm_travel.src.constants.AppConstants.Companion.recommendCities
import com.lqminhlab.mm_travel.src.resource.models.AttractionModel
import com.lqminhlab.mm_travel.src.resource.models.LocationModel
import com.lqminhlab.mm_travel.src.resource.models.ResultLocationModel
import com.lqminhlab.mm_travel.src.resource.request.SearchAttractionsRequest
import com.lqminhlab.mm_travel.src.utils.AppShared

class HomeViewModel : BaseViewModel() {

    private val _locationsSubject = MutableLiveData<List<LocationModel>>()
    private val _attractionsSubject = MutableLiveData<List<AttractionModel>>()

    val locationsSubject: LiveData<List<LocationModel>> get() = _locationsSubject
    val attractionsSubject: LiveData<List<AttractionModel>> get() = _attractionsSubject

    fun getAttractionByLocation(locationID: String) {
        setLoading(true)
        sharedRepository.searchAttractions(
            SearchAttractionsRequest(
                location_id = locationID,
                currency = "",
                lang = "vi",
                sort = "recommend",
                unit = ""
            ), {
                _attractionsSubject.postValue(it)
            }, {
                Log.e(TAG, "Message -> ${it ?: "No message"}")
            }, {
                setLoading(false)
            })
    }

    fun getCountries() {
        val locations: List<LocationModel>? = AppShared.prefs.recommendLocations;
        if (locations == null) {
            setLoading(true)
            sharedRepository.searchMultipleLocation(recommendCities, {
                _locationsSubject.postValue(it)
                AppShared.prefs.recommendLocations = it
            }, {
                Log.e(TAG, "Message -> ${it ?: "No message"}")
            }, {
                setLoading(false)
            })
        } else _locationsSubject.postValue(locations)
    }

    companion object {
        private const val TAG = "HomeViewModel:"
    }
}