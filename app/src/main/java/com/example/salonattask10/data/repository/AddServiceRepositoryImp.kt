package com.example.salonattask10.data.repository

import com.example.salonattask10.data.model.addService.AddServiceInput
import com.example.salonattask10.data.model.addServiceJON.AddServiceResponse
import com.example.salonattask10.data.model.categoriesJON.CategoriesResponse
import com.example.salonattask10.data.remote.ApiService
import com.example.salonattask10.domain.repository.AddServiceRepository
import com.example.salonattask10.domain.repository.CategoriesRepository
import javax.inject.Inject


class AddServiceRepositoryImp @Inject constructor(
    private val apiService: ApiService,

    ) : AddServiceRepository {
    override suspend fun addService(service: AddServiceInput): AddServiceResponse =
        apiService.addService(service = service)
}