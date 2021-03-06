package com.sean.blog.ui.auth

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer

import com.sean.blog.R
import com.sean.blog.model.AuthToken
import com.sean.blog.ui.auth.state.LoginData
import com.sean.blog.util.GenericApiResponse
import com.sean.blog.util.GenericApiResponse.*
import kotlinx.android.synthetic.main.fragment_login.*

/**
 * A simple [Fragment] subclass.
 */
class LoginFragment : BaseAuthFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "LoginFragment : ${viewModel.hashCode()}")

        subscribeObservers()

        login_button.setOnClickListener {
            viewModel.setAuthToken(
                AuthToken(
                    1,
                    "sadfghjdgdfgdfgdfgdfgdfgd"
                )
            )
        }

        /*
        viewModel.testLogin().observe(viewLifecycleOwner, Observer { response ->
            when(response) {
                is ApiSuccessResponse -> {
                    Log.d(TAG, "LOGIN RESPONSE : ${response.body}")
                }

                is ApiErrorResponse -> {
                    Log.d(TAG, "LOGIN RESPONSE : ${response.errorMessage}")
                }

                is ApiEmptyResponse -> {
                    Log.d(TAG, "LOGIN RESPONSE : Empty Response")
                }
            }
        })
         */
    }

    fun subscribeObservers() {
        viewModel.viewState.observe(viewLifecycleOwner, Observer {
            it.loginData?.let {loginData ->
                loginData.login_email?.let { input_email.setText(it) }
                loginData.login_password?.let { input_password.setText(it) }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.setLoginData(
            LoginData(
                input_email.text.toString(),
                input_password.text.toString()
            )
        )
    }

}
