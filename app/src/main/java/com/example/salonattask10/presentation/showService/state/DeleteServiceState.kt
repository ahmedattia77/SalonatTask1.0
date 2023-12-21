package com.example.salonattask10.presentation.showService.state

import com.example.salonattask10.data.model.deleteServiceJON.DeleteServiceResponse

data class DeleteServiceState (
    val isLoading: Boolean = false,
    val data : DeleteServiceResponse? = null,
    val error: String = ""
)