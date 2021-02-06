package com.sean.blog.ui.auth

import androidx.lifecycle.ViewModel
import com.sean.blog.repository.auth.AuthRepository

class AuthViewModel
constructor(
    val authRepository: AuthRepository
): ViewModel() {

}