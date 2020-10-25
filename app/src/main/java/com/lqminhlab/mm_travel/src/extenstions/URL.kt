package com.lqminhlab.mm_travel.src.extenstions

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.lang.Exception
import java.net.URL


// extension function to get / download bitmap from url
fun URL.toBitmap() : Bitmap?{
    return  try{
        BitmapFactory.decodeStream(openStream())
    }catch (e : Exception){
        null
    }
}