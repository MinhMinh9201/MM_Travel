package com.lqminhlab.mm_travel.src.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {

    private val _loadingSubject = MutableLiveData<Boolean>()
    private val _messageSubject = MutableLiveData<String>()

    val loadingSubject: LiveData<Boolean> get() = _loadingSubject

    fun setLoading(boolean: Boolean){
        _loadingSubject.postValue(boolean)
    }

    val messageSubject: LiveData<String> get() = _messageSubject

    fun setMessage(msg: String){
        _messageSubject.postValue(msg)
    }

    fun showToast(context : Context, msg : String){
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }

}