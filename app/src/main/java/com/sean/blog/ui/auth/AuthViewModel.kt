package com.sean.blog.ui.auth

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.sean.blog.api.auth.network_response.LoginResponse
import com.sean.blog.api.auth.network_response.RegistrationResponse
import com.sean.blog.model.AuthToken
import com.sean.blog.repository.auth.AuthRepository
import com.sean.blog.ui.BaseViewModel
import com.sean.blog.ui.DataState
import com.sean.blog.ui.auth.state.AuthStateEvent
import com.sean.blog.ui.auth.state.AuthStateEvent.*
import com.sean.blog.ui.auth.state.AuthViewState
import com.sean.blog.ui.auth.state.LoginData
import com.sean.blog.ui.auth.state.RegistrationData
import com.sean.blog.util.AbsentLiveData
import com.sean.blog.util.GenericApiResponse
import javax.inject.Inject

class AuthViewModel
@Inject
constructor(
    val authRepository: AuthRepository
): BaseViewModel<AuthStateEvent, AuthViewState>() {

    override fun handleStateEvent(stateEvent: AuthStateEvent): LiveData<DataState<AuthViewState>> {
        when(stateEvent) {
            is LoginAttempEvent -> {
                return AbsentLiveData.create()
            }

            is RegisterAttemptEvent -> {
                return AbsentLiveData.create()
            }

            is CheckPreviousAuthEvent -> {
                return AbsentLiveData.create()
            }
        }
    }

    fun setRegistrationData(registrationData: RegistrationData) {
        val update = getCurrentViewStateOrNew()
        if(update.registrationData == registrationData) {
            return
        }
        update.registrationData = registrationData
        _viewState.value = update
    }

    fun setLoginData(loginData: LoginData){
        val update = getCurrentViewStateOrNew()
        if(update.loginData == loginData){
            return
        }
        update.loginData = loginData
        _viewState.value = update
    }

    fun setAuthToken(authToken: AuthToken){
        val update = getCurrentViewStateOrNew()
        if(update.authToken == authToken){
            return
        }
        update.authToken = authToken
        _viewState.value = update
    }

    override fun initNewViewState(): AuthViewState {
        return AuthViewState()
    }

    /*
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
    */
}