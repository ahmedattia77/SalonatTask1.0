package com.example.salonattask10.presentation.login.login

import com.example.salonattask10.data.model.loginJON.LoginResponse

data class LoginState (
    val isLoading: Boolean = false,
    val data : LoginResponse? = null,
    val error: String = ""
)