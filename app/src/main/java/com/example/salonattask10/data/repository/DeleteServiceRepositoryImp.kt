package com.example.salonattask10.data.repository

import com.example.salonattask10.data.model.categoriesJON.CategoriesResponse
import com.example.salonattask10.data.model.deleteServiceJON.DeleteServiceResponse
import com.example.salonattask10.data.remote.ApiService
import com.example.salonattask10.domain.repository.CategoriesRepository
import com.example.salonattask10.domain.repository.DeleteServiceRepository
import javax.inject.Inject


class DeleteServiceRepositoryImp @Inject constructor(
    private val apiService: ApiService,

    ) : DeleteServiceRepository {
    override suspend fun deleteService(centerId: Int, serviceId: Int): DeleteServiceResponse =
        apiService.deleteService(centerId = centerId, serviceId = serviceId)

}