package com.example.salonattask10.domain.repository

import com.example.salonattask10.data.model.verifyJON.VerifyResponse

interface VerifyRepository {
    suspend fun verify (phone:String , code:String) : VerifyResponse
}