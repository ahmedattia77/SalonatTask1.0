package com.example.salonattask10.domain.manger

import kotlinx.coroutines.flow.Flow

interface LocalUserManger {

    suspend fun saveAppEntry()

    suspend fun saveToken(token :String)

    suspend fun saveCenterId(centerId:String)

    fun readAppEntry(): Flow<Boolean>

    fun readToken(): Flow<String>

    fun readCenterId(): Flow<String>
}