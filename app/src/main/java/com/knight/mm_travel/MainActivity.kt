package com.knight.mm_travel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.knight.mm_travel.src.viewmodel.UserViewModel

class MainActivity : AppCompatActivity() {

    lateinit var userViewModel : UserViewModel

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.nav_host_fragment).navigateUp()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        observeUser()
        listener()
    }

    private fun observeUser(){
        userViewModel.userSubject.observe(this, Observer<String> { showToast(it)})
    }

    private fun listener(){

    }

    private fun showToast(value: String) {
        Toast.makeText(this, value, Toast.LENGTH_LONG).show()
    }
}