package com.example.salonattask10.presentation.login.verify

import com.example.salonattask10.data.model.verifyJON.VerifyResponse

data class VerifyState(
    val isLoading: Boolean = false,
    val data : VerifyResponse? = null,
    val error: String = ""
)
