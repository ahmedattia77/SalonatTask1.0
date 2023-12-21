package com.example.salonattask10.data.remote


import com.example.salonattask10.data.model.categoriesJON.CategoriesResponse
import com.example.salonattask10.data.model.category_servicesJON.CategoryServiesResponse
import com.example.salonattask10.data.model.loginJON.LoginResponse
import com.example.salonattask10.data.model.serviceJON.ServiceResponse
import com.example.salonattask10.data.model.verifyJON.VerifyResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @FormUrlEncoded
    @POST("Back/resend")
    suspend fun login(
        @Field("phone") phone: String
    ): LoginResponse

    @FormUrlEncoded
    @POST("Back/verify")
    suspend fun verify(
        @Field("phone") phone: String,
        @Field("code") code: String
    ): VerifyResponse

    @GET("Back/services")
    suspend fun getService(
        @Query("center_id") center_id: Int
    ): ServiceResponse

    @GET("categories")
    suspend fun getCategories(): CategoriesResponse

    @GET("Back/categories")
    suspend fun getCategoryServices(
        @Query("id") id: Int
    ): CategoryServiesResponse

}