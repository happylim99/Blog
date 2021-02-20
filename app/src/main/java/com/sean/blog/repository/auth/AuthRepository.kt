package com.sean.blog.repository.auth

import androidx.lifecycle.LiveData
import com.sean.blog.api.auth.ApiAuthService
import com.sean.blog.api.auth.network_response.LoginResponse
import com.sean.blog.api.auth.network_response.RegistrationResponse
import com.sean.blog.persistence.AccountPropertiesDao
import com.sean.blog.persistence.AuthTokenDao
import com.sean.blog.session.SessionManager
import com.sean.blog.util.GenericApiResponse
import javax.inject.Inject

class AuthRepository
@Inject
constructor(
    val authTokenDao: AuthTokenDao,
    val accountPropertiesDao: AccountPropertiesDao,
    val apiAuthService: ApiAuthService,
    val sessionManager: SessionManager
){
    fun testLoginRequest(
        email:String,
        password:String
    ): LiveData<GenericApiResponse<LoginResponse>>
    {
        return apiAuthService.login(email, password)
    }

    fun testRegistrationRequest(
        email: String,
        username: String,
        password: String,
        confirmPassword: String
    ): LiveData<GenericApiResponse<RegistrationResponse>>
    {
        return apiAuthService.register(email, username, password, confirmPassword)
    }
}