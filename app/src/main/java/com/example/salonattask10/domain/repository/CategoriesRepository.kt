package com.example.salonattask10.domain.repository

import com.example.salonattask10.data.model.categoriesJON.CategoriesResponse


interface CategoriesRepository {
    suspend fun getCategories () : CategoriesResponse
}