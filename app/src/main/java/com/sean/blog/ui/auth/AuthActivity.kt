package com.sean.blog.ui.auth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sean.blog.R
import com.sean.blog.ui.BaseActivity

class AuthActivity : BaseActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
    }
}