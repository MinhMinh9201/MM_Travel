package com.lqminhlab.mm_travel.src.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lqminhlab.mm_travel.src.constants.AppConstants.Companion.euroCities
import com.lqminhlab.mm_travel.src.constants.AppConstants.Companion.featuredCities
import com.lqminhlab.mm_travel.src.constants.AppConstants.Companion.recommendCities
import com.lqminhlab.mm_travel.src.resource.models.LocationModel
import com.lqminhlab.mm_travel.src.utils.AppShared

class ExploreViewModel : BaseViewModel() {

    private val _recommendSubject = MutableLiveData<List<LocationModel>>()
    private val _euroSubject = MutableLiveData<List<LocationModel>>()
    private val _featuredSubject = MutableLiveData<List<LocationModel>>()
    private var _progress: Int = 0

    val recommendSubject: LiveData<List<LocationModel>> get() = _recommendSubject
    val euroSubject: LiveData<List<LocationModel>> get() = _euroSubject
    val featureSubject: LiveData<List<LocationModel>> get() = _featuredSubject

    fun getAttractions() {
        setLoading(true)
        val recommend: List<LocationModel>? = AppShared.prefs.recommendLocations
        val euro: List<LocationModel>? = AppShared.prefs.euroLocations
        val featured: List<LocationModel>? = AppShared.prefs.featuredLocations
        if (recommend == null) {
            sharedRepository.searchMultipleLocation(recommendCities, {
                AppShared.prefs.recommendLocations = it
                _recommendSubject.postValue(it)
            }, {
                Log.e(TAG, "Message -> ${it ?: "No message"}")
            }, {
                compileLoading()
            })
        } else {
            _recommendSubject.postValue(recommend)
            compileLoading()
        }
        if (euro == null) {
            sharedRepository.searchMultipleLocation(euroCities, {
                AppShared.prefs.euroLocations = it
                _euroSubject.postValue(it)
            }, {
                Log.e(TAG, "Message -> ${it ?: "No message"}")
            }, {
                compileLoading()
            })
        } else {
            _euroSubject.postValue(euro)
            compileLoading()
        }
        if (featured == null) {
            sharedRepository.searchMultipleLocation(featuredCities, {
                AppShared.prefs.featuredLocations = it
                _featuredSubject.postValue(it)
            }, {
                Log.e(TAG, "Message -> ${it ?: "No message"}")
            }, {
                compileLoading()
            })
        } else {
            _featuredSubject.postValue(featured)
            compileLoading()
        }
    }

    private fun compileLoading() {
        _progress += 1
        if (_progress == 3) {
            setLoading(false)
            _progress = 0
        }
    }

    companion object {
        private const val TAG = "ExploreViewModel"
    }
}