package com.sean.blog.ui.auth

import androidx.lifecycle.ViewModel
import com.sean.blog.repository.auth.AuthRepository
import javax.inject.Inject

class AuthViewModel
@Inject
constructor(
    val authRepository: AuthRepository
): ViewModel() {

}