package com.lqminhlab.mm_travel.src.resource.services

import com.lqminhlab.mm_travel.src.resource.models.LocationModel
import com.lqminhlab.mm_travel.src.resource.response.ResponseList
import com.lqminhlab.mm_travel.src.resource.models.ResultModel
import com.lqminhlab.mm_travel.src.resource.request.SearchLocationRequest
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.QueryMap

interface SharedService {

    @GET("locations/search")
    @JvmSuppressWildcards
    fun searchLocation(
        @QueryMap params: Map<String, String?>,
        @HeaderMap header: Map<String, Any>
    ): Observable<ResponseList<ResultModel<LocationModel>>>

    @GET("attractions/list")
    @JvmSuppressWildcards
    fun searchAttractions(
        @QueryMap params: Map<String, Any>,
        @HeaderMap header: Map<String, Any>
    ): Observable<ResponseList<ResultModel<Any>>>
}