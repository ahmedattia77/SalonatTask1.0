package com.example.salonattask10.domain.repository

import com.example.salonattask10.data.model.serviceJON.ServiceResponse


interface ServiceRepository {

    suspend fun getService (centerId:String) : ServiceResponse

}