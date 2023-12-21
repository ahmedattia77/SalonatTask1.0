package com.example.salonattask10.domain.repository

import com.example.salonattask10.data.model.category_servicesJON.CategoryServiesResponse


interface CategoryServicesRepository {
    suspend fun getCategories (id:Int) : CategoryServiesResponse
}