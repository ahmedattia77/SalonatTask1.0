package com.example.salonattask10.data.model.verifyJON

data class VerifyResponse(
    val `data`: Data,
    val message: String,
    val status: Boolean,
    val token: String
)