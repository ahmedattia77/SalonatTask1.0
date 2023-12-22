package com.example.salonattask10.domain.repository

import com.example.salonattask10.data.model.cateServiceJON.catServiceResponse

interface CategoryServicesRepository {
    suspend fun getCategoryServices (id:Int) : catServiceResponse
}