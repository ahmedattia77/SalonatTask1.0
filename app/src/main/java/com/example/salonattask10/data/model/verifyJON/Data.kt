package com.example.salonattask10.data.model.verifyJON

data class Data(
    val address: String,
    val address_ar: String,
    val address_en: String,
    val commercial_register_image: String,
    val days_worked: List<String>,
    val email: String,
    val end_at: String,
    val home_limit_price: Int,
    val iban_image: String,
    val id: Int,
    val image: String,
    val lat: String,
    val long: String,
    val name: String,
    val name_ar: String,
    val name_en: String,
    val phone: String,
    val start_at: String
)