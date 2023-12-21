package com.example.salonattask10.data.repository

import android.util.Log
import com.example.salonattask10.data.model.serviceJON.ServiceResponse
import com.example.salonattask10.data.remote.ApiService
import com.example.salonattask10.domain.repository.ServiceRepository
import javax.inject.Inject


class ServiceRepositoryImp @Inject constructor(
    private val apiService: ApiService,
) : ServiceRepository {

    override suspend fun getService(bearer: String , centerId:Int): ServiceResponse {
        val data = apiService.getService( center_id = centerId )
        Log.i("responseL" , data.message)
        return data
    }


}