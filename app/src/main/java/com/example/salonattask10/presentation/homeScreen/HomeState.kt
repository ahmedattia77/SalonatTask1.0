package com.example.salonattask10.presentation.homeScreen

import com.example.salonattask10.data.model.serviceJON.ServiceResponse

data class HomeState (
    val isLoading: Boolean = false,
    val data : ServiceResponse? = null,
    val error: String = ""
)