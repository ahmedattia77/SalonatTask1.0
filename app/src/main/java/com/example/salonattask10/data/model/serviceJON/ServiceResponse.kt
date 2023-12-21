package com.example.salonattask10.data.model.serviceJON

data class ServiceResponse(
    val count: Int,
    val `data`: List<Data>,
    val message: String,
    val status: Boolean
)