package com.sean.blog.ui.auth.state

import com.sean.blog.model.AuthToken

data class AuthViewState(
    var registrationData: RegistrationData? = RegistrationData(),
    var loginData: LoginData? = LoginData(),
    var authToken: AuthToken? = null
)

data class RegistrationData(
    var registration_email: String? = null,
    var registration_username: String? = null,
    var registration_password: String? = null,
    var registration_confirm_password: String? = null
)
{
    class RegistrationError {
        companion object {
            fun mustFillAllFields(): String {
                return "All fields are required"
            }

            fun passwordNotMatch(): String {
                return "Password must match"
            }

            fun none(): String {
                return "None"
            }
        }
    }

    fun isValidForRegistration(): String {
        if(registration_email.isNullOrEmpty()
            || registration_username.isNullOrEmpty()
            || registration_password.isNullOrEmpty()
            || registration_confirm_password.isNullOrEmpty())
        {
            return RegistrationError.mustFillAllFields()
        }

        if(!registration_password.equals(registration_confirm_password)) {
            return RegistrationError.passwordNotMatch()
        }

        return RegistrationError.none()
    }
}

data class LoginData(
    var login_email: String? = null,
    var login_password: String? = null
) {
    class LoginError {

        companion object{

            fun mustFillAllFields(): String{
                return "You can't login without an email and password."
            }

            fun none():String{
                return "None"
            }

        }
    }
    fun isValidForLogin(): String{

        if(login_email.isNullOrEmpty()
            || login_password.isNullOrEmpty()){

            return LoginError.mustFillAllFields()
        }
        return LoginError.none()
    }

    override fun toString(): String {
        return "LoginState(email=$login_email, password=$login_password)"
    }
}