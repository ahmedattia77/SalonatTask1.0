package com.example.salonattask10.presentation.showService

import com.example.salonattask10.data.model.services_detailsJON.ServiceDetailsResponse

data class ShowServiceState (
    val isLoading: Boolean = false,
    val data : ServiceDetailsResponse? = null,
    val error: String = ""
)