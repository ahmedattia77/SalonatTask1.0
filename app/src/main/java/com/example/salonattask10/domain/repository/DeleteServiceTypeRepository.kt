package com.example.salonattask10.domain.repository

import com.example.salonattask10.data.model.deleteServiceJON.DeleteServiceResponse


interface DeleteServiceTypeRepository {
    suspend fun deleteServiceType(centerId: Int, serviceId: Int, typeId: Int): DeleteServiceResponse
}