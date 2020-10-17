package com.lqminhlab.mm_travel.src.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseUser
import com.lqminhlab.mm_travel.R
import com.lqminhlab.mm_travel.src.extenstions.isValidEmail
import com.lqminhlab.mm_travel.src.extenstions.isValidLength
import com.lqminhlab.mm_travel.src.extenstions.validate
import com.lqminhlab.mm_travel.src.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.loading_normal.*

class LoginFragment : Fragment() {

    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
        obverse()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        listener()
    }

    private fun obverse() {
        userViewModel.loadingSubject.observe(this, Observer<Boolean> {
            loading_normal.visibility = if (it) View.VISIBLE else View.GONE
        })
        userViewModel.userSubject.observe(this, Observer<FirebaseUser> {
            if (it != null && activity != null) {
                this.findNavController().navigate(R.id.action_loginFragment_to_menuFragment)
            }
        })
        userViewModel.messageSubject.observe(this, Observer<String> {
            if(it.isNullOrEmpty()) txt_login_error.visibility = View.GONE
            else {
                txt_login_error.visibility = View.VISIBLE
                txt_login_error.text = it
            }
        })
    }

    private fun listener() {
        btn_login.setOnClickListener { view ->
            if (!editor_login_password.text.toString()
                    .isValidLength(LENGTH_PASSWORD) || !editor_login_username.text.toString()
                    .isValidEmail()
            ) {
                if (!editor_login_password.validate(
                        { s: String -> s.isValidLength(LENGTH_PASSWORD) },
                        getString(R.string.validate_password)
                    )
                ) {
                    editor_login_password.error = getString(R.string.validate_password)
                }
                if (!editor_login_username.validate(
                        { s: String -> s.isValidEmail() },
                        getString(R.string.validate_username)
                    )
                ) {
                    editor_login_username.error = getString(R.string.validate_username)
                }
            } else {
                userViewModel.loginUser(
                    editor_login_username.text.toString(),
                    editor_login_password.text.toString()
                )
            }
        }
    }

    companion object {
        private const val LENGTH_PASSWORD = 6
    }
}