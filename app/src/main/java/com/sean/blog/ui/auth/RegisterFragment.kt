package com.sean.blog.ui.auth

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import com.sean.blog.R
import com.sean.blog.util.GenericApiResponse
import com.sean.blog.util.GenericApiResponse.*

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
    }

}
