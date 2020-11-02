package com.lqminhlab.mm_travel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.lqminhlab.mm_travel.src.resource.Client
import com.lqminhlab.mm_travel.src.viewmodel.BaseViewModel

class MainActivity : AppCompatActivity() {

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.nav_host_fragment).navigateUp()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initialized()
        observeUser()
        listener()
    }

    private fun initialized() {
        //instance client initialized
        Client()
    }

    private fun observeUser() {

    }

    private fun listener() {

    }
}