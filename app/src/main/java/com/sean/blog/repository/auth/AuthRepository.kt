package com.sean.blog.repository.auth

import com.sean.blog.api.auth.ApiAuthService
import com.sean.blog.persistence.AccountPropertiesDao
import com.sean.blog.persistence.AuthTokenDao
import com.sean.blog.session.SessionManager

class AuthRepository
constructor(
    val authTokenDao: AuthTokenDao,
    val accountPropertiesDao: AccountPropertiesDao,
    val apiAuthService: ApiAuthService,
    val sessionManager: SessionManager
){
}