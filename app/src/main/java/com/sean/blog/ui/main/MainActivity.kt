package com.sean.blog.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.lifecycle.Observer
import com.sean.blog.R
import com.sean.blog.ui.BaseActivity
import com.sean.blog.ui.auth.AuthActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity: BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        subscribeObservers()

        tool_bar.setOnClickListener {
            sessionManager.logout()
        }
    }

    fun subscribeObservers() {
        sessionManager.cachedToken.observe(this, Observer {authToken ->
            Log.d(TAG, "MainActivity: subscribeObservers: Authtoken: ${authToken}")
            if(authToken == null || authToken.account_pk == -1 || authToken.token == null) {
                navAuthActivity()
                finish()
            }
        })
    }

    private fun navAuthActivity() {
        val intent = Intent(this, AuthActivity::class.java)
        startActivity(intent)
        finish()
    }

}
