package com.sean.blog.session

import android.app.Application
import com.sean.blog.persistence.AuthTokenDao

class SessionManager
constructor(
    val authTokenDao: AuthTokenDao,
    val application: Application
){
}