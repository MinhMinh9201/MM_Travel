package com.lqminhlab.mm_travel.src.resource.repositorires

import android.util.Log
import com.lqminhlab.mm_travel.src.constants.AppConstants
import com.lqminhlab.mm_travel.src.resource.Client
import com.lqminhlab.mm_travel.src.resource.models.LocationModel
import com.lqminhlab.mm_travel.src.resource.models.ResultModel
import com.lqminhlab.mm_travel.src.resource.request.SearchAttractionsRequest
import com.lqminhlab.mm_travel.src.resource.request.SearchLocationRequest
import com.lqminhlab.mm_travel.src.resource.response.ResponseList
import com.lqminhlab.mm_travel.src.resource.services.SharedService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlin.collections.ArrayList

class SharedRepository {
    private var service: SharedService = Client.instance.create(SharedService::class.java)

    fun searchMultipleLocation(
        requests: List<SearchLocationRequest>,
        onSuccess: (data: List<LocationModel>) -> Unit,
        onError: (message: String?) -> Unit,
        onComplete: () -> Unit
    ) {
        val observables: ArrayList<Observable<*>> = ArrayList<Observable<*>>()
        for (request in requests) {
            observables.add(service.searchLocation(request.toJSON(), AppConstants.baseHeader))
        }
        Observable.zip(observables) {
            val data: List<ResponseList<ResultModel<LocationModel>>> =
                it.toList() as List<ResponseList<ResultModel<LocationModel>>>
            val locations: ArrayList<LocationModel> = ArrayList<LocationModel>()
            for (item in data) {
                locations.add(item.data.find { resultModel ->
                    resultModel.isTop
                }?.data ?: item.data.first().data)
            }
            locations
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                onSuccess(result)
            }, { error ->
                onError(error.message)
            }) {
                onComplete.invoke()
            }
    }

    fun searchLocation(
        request: SearchLocationRequest,
        onSuccess: (data: List<ResultModel<LocationModel>>) -> Unit,
        onError: (message: String?) -> Unit
    ) {
        service.searchLocation(request.toJSON(), AppConstants.baseHeader)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe(
                { result -> onSuccess(result.data) },
                { error -> onError(error.message) }
            )
    }

    fun searchMultipleAttractions(
        requests: List<SearchAttractionsRequest>,
        onSuccess: (data: List<Any>) -> Unit,
        onError: (message: String?) -> Unit,
        onComplete: () -> Unit
    ) {
        val observables: ArrayList<Observable<*>> = ArrayList<Observable<*>>()
        for (request in requests) {
            observables.add(service.searchAttractions(request.toJSON(), AppConstants.baseHeader))
        }
        Observable.zip(observables) {
            Log.e(TAG, it[0].toString())
            val data: List<ResponseList<ResultModel<Any>>> =
                it.toList() as List<ResponseList<ResultModel<Any>>>
            val attractions: ArrayList<Any> = ArrayList<Any>()
            for (item in data) {
                attractions.add(item.data.find { resultModel ->
                    resultModel.isTop
                }?.data ?: item.data.first().data)
            }
            attractions
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                onSuccess(result)
            }, { error ->
                onError(error.message)
            }) {
                onComplete.invoke()
            }
    }

    fun searchAttractions(
        request: SearchAttractionsRequest,
        onSuccess: (data: List<ResultModel<Any>>) -> Unit,
        onError: (message: String?) -> Unit
    ) {
        service.searchAttractions(request.toJSON(), AppConstants.baseHeader).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe(
                { result -> onSuccess(result.data) },
                { error -> onError(error.message) }
            )
    }

    companion object {
        fun getInstance(): SharedRepository = instance
        private val instance: SharedRepository = SharedRepository()
        private const val TAG = "SharedRepository:"
    }
}