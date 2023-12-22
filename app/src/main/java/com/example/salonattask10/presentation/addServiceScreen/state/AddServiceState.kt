package com.example.salonattask10.presentation.addServiceScreen.state

import com.example.salonattask10.data.model.addServiceJON.AddServiceResponse
data class AddServiceState (
    val isLoading: Boolean = false,
    val data : AddServiceResponse? = null,
    val error: String = ""
)