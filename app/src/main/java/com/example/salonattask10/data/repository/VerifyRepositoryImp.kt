package com.example.salonattask10.data.repository

import android.util.Log
import com.example.salonattask10.data.model.verifyJON.VerifyResponse
import com.example.salonattask10.data.remote.ApiService
import com.example.salonattask10.domain.repository.VerifyRepository
import javax.inject.Inject

class VerifyRepositoryImp @Inject constructor(
    private val apiService: ApiService,
) : VerifyRepository {
    override suspend fun verify(phone: String, code: String): VerifyResponse {
        val data = apiService.verify(phone = phone, code = code)
        return data
    }
}