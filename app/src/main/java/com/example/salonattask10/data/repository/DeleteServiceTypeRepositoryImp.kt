package com.example.salonattask10.data.repository

import com.example.salonattask10.data.model.categoriesJON.CategoriesResponse
import com.example.salonattask10.data.model.deleteServiceJON.DeleteServiceResponse
import com.example.salonattask10.data.remote.ApiService
import com.example.salonattask10.domain.repository.CategoriesRepository
import com.example.salonattask10.domain.repository.DeleteServiceRepository
import com.example.salonattask10.domain.repository.DeleteServiceTypeRepository
import javax.inject.Inject


class DeleteServiceTypeRepositoryImp @Inject constructor(
    private val apiService: ApiService,

    ) : DeleteServiceTypeRepository {
    override suspend fun deleteServiceType(
        centerId: Int,
        serviceId: Int,
        typeId: Int
    ): DeleteServiceResponse =
        apiService.deleteServiceType(
            centerId = centerId,
            serviceId = serviceId,
            serviceTypeId = typeId
        )


}