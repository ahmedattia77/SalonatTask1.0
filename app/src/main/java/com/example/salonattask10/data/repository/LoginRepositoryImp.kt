package com.example.salonattask10.data.repository

import android.util.Log
import com.example.salonattask10.data.model.loginJON.LoginResponse
import com.example.salonattask10.data.remote.ApiService
import com.example.salonattask10.domain.repository.LoginRepository
import com.example.salonattask10.domain.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject


class LoginRepositoryImp @Inject constructor(
    private val apiService: ApiService,

    ) : LoginRepository {
    //    override suspend fun login(phone: String): Flow<Resource<LoginResponse>> {
//        return flow {
//            try {
//                emit(Resource.Loading())
//                val data = apiService.login(phone = phone)
//                Log.i("loginResponse" , data.message.toString())
//                emit(Resource.Success(data))
//
//            } catch (e: Exception) {
//                emit(Resource.Error("${e.localizedMessage} : An unexpected error happened"))
//            } catch (e: IOException) {
//                emit(Resource.Error("${e.localizedMessage} : An unexpected error happened"))
//            } catch (e: HttpException) {
//                emit(Resource.Error("${e.localizedMessage} : An unexpected error happened"))
//            }
//        }
//    }

    override suspend fun login(phone: String): LoginResponse {
        val data =  apiService.login(phone = phone)
        return data
    }


}