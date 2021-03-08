package com.sean.blog.repository.auth

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.switchMap
import com.sean.blog.api.auth.ApiAuthService
import com.sean.blog.api.auth.network_response.LoginResponse
import com.sean.blog.api.auth.network_response.RegistrationResponse
import com.sean.blog.model.AuthToken
import com.sean.blog.persistence.AccountPropertiesDao
import com.sean.blog.persistence.AuthTokenDao
import com.sean.blog.session.SessionManager
import com.sean.blog.ui.DataState
import com.sean.blog.ui.Response
import com.sean.blog.ui.ResponseType
import com.sean.blog.ui.auth.AuthViewModel
import com.sean.blog.ui.auth.state.AuthViewState
import com.sean.blog.util.ErrorHandling.Companion.ERROR_UNKNOWN
import com.sean.blog.util.GenericApiResponse
import javax.inject.Inject

class AuthRepository
@Inject
constructor(
    val authTokenDao: AuthTokenDao,
    val accountPropertiesDao: AccountPropertiesDao,
    val apiAuthService: ApiAuthService,
    val sessionManager: SessionManager
)
{
    fun attemptLogin(email: String, password: String): LiveData<DataState<AuthViewState>> {
        Log.d("aaabbb", "trigger login")
        return apiAuthService.login(email, password)
            .switchMap { response ->
                object: LiveData<DataState<AuthViewState>>() {
                    override fun onActive() {
                        super.onActive()

                        when(response) {
                            is GenericApiResponse.ApiSuccessResponse -> {
                                value = DataState.data(
                                    data = AuthViewState(
                                        authToken = AuthToken(
                                            response.body.pk,
                                            response.body.token
                                        )
                                    ),
                                    response = null
                                )
                            }

                            is GenericApiResponse.ApiErrorResponse -> {
                                value = DataState.error(
                                    response = Response(
                                        message = response.errorMessage,
                                        responseType = ResponseType.Dialog()
                                    )
                                )
                            }

                            is GenericApiResponse.ApiEmptyResponse -> {
                                value = DataState.error(
                                    response = Response(
                                        message = ERROR_UNKNOWN,
                                        responseType = ResponseType.Dialog()
                                    )
                                )
                            }
                        }
                    }
                }
            }
    }

    fun attemptRegistration(
        email: String,
        username: String,
        password: String,
        confirmPassword: String
    ): LiveData<DataState<AuthViewState>> {
        return apiAuthService.register(email, username, password, confirmPassword)
            .switchMap { response ->
                object: LiveData<DataState<AuthViewState>>() {
                    override fun onActive() {
                        super.onActive()

                        when(response) {
                            is GenericApiResponse.ApiSuccessResponse -> {
                                value = DataState.data(
                                    data = AuthViewState(
                                        authToken = AuthToken(
                                            response.body.pk,
                                            response.body.token
                                        )
                                    ),
                                    response = null
                                )
                            }

                            is GenericApiResponse.ApiErrorResponse -> {
                                value = DataState.error(
                                    response = Response(
                                        message = response.errorMessage,
                                        responseType = ResponseType.Dialog()
                                    )
                                )
                            }

                            is GenericApiResponse.ApiEmptyResponse -> {
                                value = DataState.error(
                                    response = Response(
                                        message = ERROR_UNKNOWN,
                                        responseType = ResponseType.Dialog()
                                    )
                                )
                            }
                        }
                    }
                }
            }
    }

    /*
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
     */
}