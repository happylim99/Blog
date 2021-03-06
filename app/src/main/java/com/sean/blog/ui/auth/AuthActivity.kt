package com.sean.blog.ui.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.sean.blog.R
import com.sean.blog.ui.BaseActivity
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

        viewModel.viewState.observe(this, Observer {
            it.authToken?.let {
                Log.d("abcabc", "uhuhuhuuhuh")
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