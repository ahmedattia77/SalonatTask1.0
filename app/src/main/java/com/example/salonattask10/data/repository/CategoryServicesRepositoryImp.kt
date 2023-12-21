package com.example.salonattask10.data.repository

import com.example.salonattask10.data.model.categoriesJON.CategoriesResponse
import com.example.salonattask10.data.model.category_servicesJON.CategoryServiesResponse
import com.example.salonattask10.data.remote.ApiService
import com.example.salonattask10.domain.repository.CategoriesRepository
import com.example.salonattask10.domain.repository.CategoryServicesRepository
import javax.inject.Inject


class CategoryServicesRepositoryImp @Inject constructor(
    private val apiService: ApiService,

    ) : CategoryServicesRepository {
    override suspend fun getCategories(id: Int): CategoryServiesResponse =
        apiService.getCategoryServices(id = id)

}