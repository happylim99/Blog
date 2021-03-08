package com.sean.blog.ui.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.sean.blog.R
import com.sean.blog.ui.BaseActivity
import com.sean.blog.ui.ResponseType
import com.sean.blog.ui.auth.state.AuthViewState
import com.sean.blog.ui.main.MainActivity
import com.sean.blog.viewmodel.ViewModelProviderFactory
import javax.inject.Inject

class AuthActivity : BaseActivity() {

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    lateinit var viewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        viewModel = ViewModelProvider(this, providerFactory).get(AuthViewModel::class.java)

        subscribeObservers()
    }

    fun subscribeObservers() {
        Log.d("aaabbb", "auth activity")
        viewModel.dataState.observe(this, Observer { dataState ->
            dataState.data?.let { data ->
                data.data?.let { event ->
                    event.getContentIfNotHandled()?.let {
                        it.authToken?.let {
                            Log.d(TAG, "AuthActivity, DataState: ${it}")
                            viewModel.setAuthToken(it)
                        }
                    }
                }

                data.response?.let { event ->
                    event.getContentIfNotHandled()?.let {
                        when(it.responseType) {
                            is ResponseType.Dialog -> {
                                //inflate error dialog
                            }

                            is ResponseType.Toast -> {

                            }

                            is ResponseType.None -> {
                                Log.d(TAG, "AuthActivity, Response: ${it.message}")
                            }
                        }
                    }
                }
            }
        })

        viewModel.viewState.observe(this, Observer {
            it.authToken?.let {
                sessionManager.login(it)
            }
        })
        sessionManager.cachedToken.observe(this, Observer {authToken ->
            Log.d(TAG, "MainActivity: subscribeObservers: Authtoken: ${authToken}")
            if(authToken != null && authToken.account_pk != -1 && authToken.token != null) {
                navAuthActivity()
//                finish()
            }
        })
    }

    private fun navAuthActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
//        finish()
    }

}