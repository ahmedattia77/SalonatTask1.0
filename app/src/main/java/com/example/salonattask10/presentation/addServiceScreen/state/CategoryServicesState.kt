package com.example.salonattask10.presentation.addServiceScreen.state

import com.example.salonattask10.data.model.category_servicesJON.CategoryServiesResponse

data class CategoryServicesState (
    val isLoading: Boolean = false,
    val data : CategoryServiesResponse? = null,
    val error: String = ""
)