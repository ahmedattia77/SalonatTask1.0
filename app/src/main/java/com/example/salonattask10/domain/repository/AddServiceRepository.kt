package com.example.salonattask10.domain.repository

import com.example.salonattask10.data.model.addService.AddServiceInput
import com.example.salonattask10.data.model.addServiceJON.AddServiceResponse
import com.example.salonattask10.data.model.deleteServiceJON.DeleteServiceResponse


interface AddServiceRepository {
    suspend fun addService(service : AddServiceInput): AddServiceResponse
}