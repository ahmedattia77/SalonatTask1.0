package com.example.salonattask10.presentation.addServiceScreen.state

import com.example.salonattask10.data.model.categoriesJON.CategoriesResponse

data class CategoryState (
    val isLoading: Boolean = false,
    val data : CategoriesResponse? = null,
    val error: String = ""
)