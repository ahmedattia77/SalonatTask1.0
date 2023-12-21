package com.example.salonattask10.domain.repository

import com.example.salonattask10.data.model.loginJON.LoginResponse
import com.example.salonattask10.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

interface LoginRepository {
//    suspend fun login (phone:String) : Flow<Resource<LoginResponse>>
    suspend fun login (phone:String) : LoginResponse
}