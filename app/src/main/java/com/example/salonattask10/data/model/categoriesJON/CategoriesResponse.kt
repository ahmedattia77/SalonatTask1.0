package com.example.salonattask10.data.model.categoriesJON

data class CategoriesResponse(
    val `data`: List<Data>,
    val message: Any,
    val orders: List<Any>,
    val status: Boolean
)