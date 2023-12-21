package com.example.salonattask10.domain.repository

import com.example.salonattask10.data.model.services_detailsJON.ServiceDetailsResponse


interface ServiceDetailsRepository {
    suspend fun getServiceDetails(centerId: Int, serviceId: Int): ServiceDetailsResponse
}