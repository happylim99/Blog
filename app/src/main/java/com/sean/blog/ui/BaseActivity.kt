package com.sean.blog.ui

import com.sean.blog.session.SessionManager
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity: DaggerAppCompatActivity() {

    protected val TAG: String = "BaseActivity MyDebug"

    @Inject
    lateinit var sessionManager: SessionManager
}