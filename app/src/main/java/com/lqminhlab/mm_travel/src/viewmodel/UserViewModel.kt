package com.lqminhlab.mm_travel.src.viewmodel

import android.app.Activity
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.lqminhlab.mm_travel.src.resource.models.LocationModel
import com.lqminhlab.mm_travel.src.resource.models.ResultModel
import com.lqminhlab.mm_travel.src.resource.request.SearchLocationRequest
import java.util.*
import kotlin.concurrent.schedule

class UserViewModel : BaseViewModel() {

    private var auth: FirebaseAuth = Firebase.auth
    private val _userSubject = MutableLiveData<FirebaseUser>()

val userSubject: LiveData<FirebaseUser> get() = _userSubject

    fun onSuccess(data : List<ResultModel<LocationModel>>){

    }

    fun onError(message : String?){

    }

    fun findUserWithLogin(activity: Activity?, ifLogin: ()->Unit, ifNot : ()->Unit){
        Log.d(TAG, "auth.currentUser ${auth.currentUser?.email ?: "Email"}")
        Timer("", false).schedule(2000){
            activity?.runOnUiThread {
                Runnable {
                    setLoading(true)
                    if(auth.currentUser != null){
                        Timer("", false).schedule(1000){
                            _userSubject.postValue(auth.currentUser)
                            ifLogin.invoke()
                        }
                    }else
                    {
                        ifNot.invoke()
                        setLoading(false)
                    }
                }.run()
            }
        }
    }

    fun createUser(username: String, password: String, rePassword: String, callback: () -> Any) {
        if (password != rePassword) {
            setMessage("Password ot match!")
        }
        setLoading(true)
        auth.createUserWithEmailAndPassword(username, password).addOnCompleteListener { task ->
            if(task.isSuccessful) {
                callback.invoke()
                Log.d(TAG, "signUpWithEmail:success")
                auth.signOut()
            } else {
                Log.d(TAG, "signUpWithEmail:Failure ${task.exception?.message ?: ""}")
            }
            setMessage(task.exception?.message ?: "")
            setLoading(false)
        }
    }

    fun loginUser(username: String, password: String) {
        setLoading(true)
        auth.signInWithEmailAndPassword(username, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                _userSubject.postValue(auth.currentUser)
                Log.d(TAG, "signInWithEmail:success")
            } else {
                Log.d(TAG, "signInWithEmail:Failure ${task.exception?.message ?: ""}")
            }
            setMessage(task.exception?.message ?: "")
            setLoading(false)
        }
    }

    override fun onCleared() {
        super.onCleared()
    }

    companion object {
        private const val TAG = "UserViewModel"
    }
}