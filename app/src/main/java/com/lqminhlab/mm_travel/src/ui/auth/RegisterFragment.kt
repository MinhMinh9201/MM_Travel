package com.lqminhlab.mm_travel.src.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.lqminhlab.mm_travel.R
import com.lqminhlab.mm_travel.src.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_register.*
import kotlinx.android.synthetic.main.loading_normal.*
import java.lang.Exception

class RegisterFragment : Fragment() {

    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userViewModel = activity?.let { ViewModelProviders.of(it)[UserViewModel::class.java] }
            ?: throw Exception("Activity null")
        obverse()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        listener()
    }

    private fun obverse() {
        userViewModel.loadingSubject.observe(this, Observer<Boolean> {
            loading_normal.visibility = if (it) VISIBLE else GONE
        })
        userViewModel.messageSubject.observe(this, Observer<String> {
            if(it.isNullOrEmpty()) txt_register_error.visibility = GONE
            else {
                txt_register_error.visibility = VISIBLE
                txt_register_error.text = it
            }
        })
    }

    private fun listener() {
        btn_register.setOnClickListener { view ->
            if (editor_register_username.text != null && editor_register_password.text != null && editor_register_repassword.text != null)
                userViewModel.createUser(
                    editor_register_username.text.toString(),
                    editor_register_password.text.toString(),
                    editor_register_repassword.text.toString()
                ) {
                    this.findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
                }
        }
    }
}