package com.knight.mm_travel.src.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserViewModel : ViewModel(){

    private val _userSubject = MutableLiveData<String>()
    var userName = "Minh Minh"

    val userSubject : LiveData<String> get() = _userSubject

    fun getName() : String?{
        return  _userSubject.value
    }

    fun updateName(name : String){
        _userSubject.postValue(name)
    }

    override fun onCleared() {
        super.onCleared()
    }
}