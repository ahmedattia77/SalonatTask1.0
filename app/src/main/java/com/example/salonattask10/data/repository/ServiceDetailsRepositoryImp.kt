package com.example.salonattask10.data.repository

import com.example.salonattask10.data.model.services_detailsJON.ServiceDetailsResponse
import com.example.salonattask10.data.remote.ApiService
import com.example.salonattask10.domain.repository.ServiceDetailsRepository
import javax.inject.Inject


class ServiceDetailsRepositoryImp @Inject constructor(
    private val apiService: ApiService,

    ) : ServiceDetailsRepository {
    override suspend fun getServiceDetails(centerId: Int, serviceId: Int): ServiceDetailsResponse =
        apiService.showService(centerId = centerId, serviceId = serviceId)
}