package com.lqminhlab.mm_travel.src.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lqminhlab.mm_travel.src.constants.AppConstants.Companion.euroCities
import com.lqminhlab.mm_travel.src.constants.AppConstants.Companion.recommendCities
import com.lqminhlab.mm_travel.src.resource.models.LocationModel
import com.lqminhlab.mm_travel.src.resource.request.SearchLocationRequest
import com.lqminhlab.mm_travel.src.utils.AppShared

class HomeViewModel : BaseViewModel() {

    private val _countriesSubject = MutableLiveData<List<LocationModel>>()

    val countriesSubject: LiveData<List<LocationModel>> get() = _countriesSubject

    fun getCountries() {
        val locations: List<LocationModel>? = AppShared.prefs.recommendLocations;
        if (locations == null) {
            setLoading(true)
            sharedRepository.searchMultipleLocation(recommendCities, {
                _countriesSubject.postValue(it)
                AppShared.prefs.recommendLocations = it
            }, {
                Log.e(TAG, "Message -> ${it ?: "No message"}")
            }, {
                setLoading(false)
            })
        } else _countriesSubject.postValue(locations)
    }

    companion object {
        private const val TAG = "HomeViewModel:"
    }
}