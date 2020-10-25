package com.lqminhlab.mm_travel.src.resource

import com.lqminhlab.mm_travel.src.constants.AppConstants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class Client(baseUrl: String? = null) {

    private var url: String? = baseUrl

    init {
        val client: OkHttpClient =
            OkHttpClient().newBuilder()
                .connectTimeout(AppConstants.connectTimeout, TimeUnit.SECONDS)
                .readTimeout(AppConstants.readTimeout, TimeUnit.SECONDS)
                .writeTimeout(AppConstants.writeTimeout, TimeUnit.SECONDS)
                .build()
        instance = Retrofit.Builder()
            .baseUrl(url ?: AppConstants.baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(client)
            .build()
    }

    companion object {
        lateinit var instance: Retrofit
    }
}