package com.example.salonattask10.data.remote


import com.example.salonattask10.data.model.addService.AddServiceInput
import com.example.salonattask10.data.model.addServiceJON.AddServiceResponse
import com.example.salonattask10.data.model.cateServiceJON.catServiceResponse
import com.example.salonattask10.data.model.categoriesJON.CategoriesResponse
import com.example.salonattask10.data.model.category_servicesJON.CategoryServiesResponse
import com.example.salonattask10.data.model.deleteServiceJON.DeleteServiceResponse
import com.example.salonattask10.data.model.loginJON.LoginResponse
import com.example.salonattask10.data.model.serviceJON.ServiceResponse
import com.example.salonattask10.data.model.services_detailsJON.ServiceDetailsResponse
import com.example.salonattask10.data.model.verifyJON.VerifyResponse
import com.example.salonattask10.presentation.addServiceScreen.AddServiceViewModel_Factory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
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

    //categories/5/services
    @GET("categories/{id}/services")
    suspend fun getCategoryServices(
        @Path("id") id: Int
    ): catServiceResponse

    @GET("Back/show/center/{center_id}/service/{service_id}")
    suspend fun showService(
        @Path("center_id") centerId: Int,
        @Path("service_id") serviceId: Int,
    ): ServiceDetailsResponse

    @DELETE("Back/delete/center/{center_id}/service/{service_id}")
    suspend fun deleteService(
        @Path("center_id") centerId: Int,
        @Path("service_id") serviceId: Int,
    ): DeleteServiceResponse

    @DELETE("Back/delete/center/{center_id}/service/{service_id}/type/{service_type_id}")
    suspend fun deleteServiceType(
        @Path("center_id") centerId: Int,
        @Path("service_id") serviceId: Int,
        @Path("service_type_id") serviceTypeId: Int,
    ): DeleteServiceResponse

    @POST("Back/addservice")
    suspend fun addService(
        @Body service: AddServiceInput
    ): AddServiceResponse


}