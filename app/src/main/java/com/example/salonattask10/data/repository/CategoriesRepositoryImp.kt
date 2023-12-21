package com.example.salonattask10.data.repository

import com.example.salonattask10.data.model.categoriesJON.CategoriesResponse
import com.example.salonattask10.data.remote.ApiService
import com.example.salonattask10.domain.repository.CategoriesRepository
import javax.inject.Inject


class CategoriesRepositoryImp @Inject constructor(
    private val apiService: ApiService,

    ) : CategoriesRepository {
    override suspend fun getCategories(): CategoriesResponse =
        apiService.getCategories()
}