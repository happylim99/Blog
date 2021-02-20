package com.sean.blog.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.sean.blog.api.auth.network_response.LoginResponse
import com.sean.blog.api.auth.network_response.RegistrationResponse
import com.sean.blog.repository.auth.AuthRepository
import com.sean.blog.util.GenericApiResponse
import javax.inject.Inject

class AuthViewModel
@Inject
constructor(
    val authRepository: AuthRepository
): ViewModel() {

    fun testLogin(): LiveData<GenericApiResponse<LoginResponse>> {
        return authRepository.testLoginRequest(
            "happylim99@gmail.com",
            "Dontgrid99"
        )
    }

    fun testRegister(): LiveData<GenericApiResponse<RegistrationResponse>> {
        return authRepository.testRegistrationRequest(
            "testreg111@yahoo.com",
            "testreg111",
            "testreg111",
            "testreg111"
        )
    }
}