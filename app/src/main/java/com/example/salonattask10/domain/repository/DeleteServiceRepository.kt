package com.example.salonattask10.domain.repository

import com.example.salonattask10.data.model.deleteServiceJON.DeleteServiceResponse


interface DeleteServiceRepository {
    suspend fun deleteService(centerId: Int, serviceId: Int): DeleteServiceResponse
}