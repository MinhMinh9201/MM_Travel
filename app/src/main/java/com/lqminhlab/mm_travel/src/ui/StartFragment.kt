package com.lqminhlab.mm_travel.src.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.lqminhlab.mm_travel.R
import com.lqminhlab.mm_travel.src.viewmodel.UserViewModel
import kotlinx.android.synthetic.*

import kotlinx.android.synthetic.main.fragment_start.*
import kotlinx.android.synthetic.main.loading_normal.*
import java.lang.Exception

class StartFragment : Fragment() {

    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userViewModel = activity?.let { ViewModelProviders.of(it)[UserViewModel::class.java] }
            ?: throw Exception("Activity null")
        obverseUser()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_start, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        findUserAndLogin()
        listener()
    }

    override fun onDestroy() {
        super.onDestroy()
        this.clearFindViewByIdCache()
    }

    private fun findUserAndLogin() {
        userViewModel.findUserWithLogin(activity, {
            this.findNavController().navigate(R.id.action_startFragment_to_menuFragment)
        }, {
            layout_start_action.visibility = VISIBLE
        })
    }

    private fun obverseUser() {
        userViewModel.loadingSubject.observe(this, Observer<Boolean> {
            loading_normal.visibility = if (it) View.VISIBLE else View.GONE
        })
    }

    private fun listener() {
        btn_start_login.setOnClickListener { view ->
            this.findNavController().navigate(R.id.action_startFragment_to_loginFragment)
        }
        btn_start_register.setOnClickListener { view ->
            this.findNavController().navigate(R.id.action_startFragment_to_registerFragment)
        }
    }
}