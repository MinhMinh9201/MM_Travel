package com.lqminhlab.mm_travel.src.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lqminhlab.mm_travel.src.resource.models.LocationModel
import com.lqminhlab.mm_travel.src.resource.request.SearchLocationRequest

class HomeViewModel : BaseViewModel() {

    private val countries: ArrayList<SearchLocationRequest> = arrayListOf(
        SearchLocationRequest("china"),
        SearchLocationRequest("japan"),
        SearchLocationRequest("korea")
    )

    private val _countriesSubject = MutableLiveData<List<LocationModel>>()

    val countriesSubject : LiveData<List<LocationModel>> get() = _countriesSubject

    fun pushList(){
        _countriesSubject.postValue(ArrayList())
    }

    fun getCountries() {
        setLoading(true)
        sharedRepository.searchMultipleLocation(countries, {
            _countriesSubject.postValue(it)
        }, {
            Log.e(TAG, "Message -> ${it ?: "No message"}")
        }, {
            setLoading(false)
        })
    }

    companion object{
        private const val TAG = "HomeViewModel:"
    }
}