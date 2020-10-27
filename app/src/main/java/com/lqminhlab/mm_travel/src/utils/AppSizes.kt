package com.lqminhlab.mm_travel.src.utils

import android.app.Activity
import android.content.Context
import android.util.DisplayMetrics

class AppSizes(val context: Context) {
    private val diametric = DisplayMetrics()
    var width: Int
    var height: Int

    init {
        (context as Activity).windowManager.defaultDisplay.getMetrics(diametric)
        width = diametric.widthPixels
        height = diametric.heightPixels
    }
}