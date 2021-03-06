package com.sean.blog.ui.auth

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import com.sean.blog.R
import com.sean.blog.ui.auth.state.RegistrationData
import com.sean.blog.util.GenericApiResponse
import com.sean.blog.util.GenericApiResponse.*
import kotlinx.android.synthetic.main.fragment_register.*

/**
 * A simple [Fragment] subclass.
 */
class RegisterFragment : BaseAuthFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "RegisterFragment : ${viewModel.hashCode()}")

        subscribeObservers()

        /*
        viewModel.testRegister().observe(viewLifecycleOwner, Observer { response ->
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
            it.registrationData?.let {registrationData ->
                registrationData.registration_email?.let { input_email.setText(it) }
                registrationData.registration_username?.let { input_username.setText(it) }
                registrationData.registration_password?.let { input_password.setText(it) }
                registrationData.registration_confirm_password?.let { input_password_confirm.setText(it) }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.setRegistrationData(
            RegistrationData(
                input_email.text.toString(),
                input_username.text.toString(),
                input_password.text.toString(),
                input_password_confirm.text.toString()
            )
        )
    }

}
