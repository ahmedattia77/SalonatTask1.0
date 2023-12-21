package com.example.salonattask10.di

import android.app.Application
import com.example.salonattask10.Constants
import com.example.salonattask10.data.local.LocalUserMangerImp
import com.example.salonattask10.data.remote.ApiService
import com.example.salonattask10.data.repository.CategoriesRepositoryImp
import com.example.salonattask10.data.repository.CategoryServicesRepositoryImp
import com.example.salonattask10.data.repository.LoginRepositoryImp
import com.example.salonattask10.data.repository.VerifyRepositoryImp
import com.example.salonattask10.domain.manger.LocalUserManger
import com.example.salonattask10.domain.repository.CategoriesRepository
import com.example.salonattask10.domain.repository.CategoryServicesRepository
import com.example.salonattask10.domain.repository.LoginRepository
import com.example.salonattask10.domain.repository.VerifyRepository
import com.example.salonattask10.domain.usecase.app_entry.AppEntryUseCases
import com.example.salonattask10.domain.usecase.app_entry.ReadCenterId
import com.example.salonattask10.domain.usecase.app_entry.ReadToken
import com.example.salonattask10.domain.usecase.app_entry.ReadUserEntry
import com.example.salonattask10.domain.usecase.app_entry.SaveCenterId
import com.example.salonattask10.domain.usecase.app_entry.SaveToken
import com.example.salonattask10.domain.usecase.app_entry.SaveUserEntry
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMovieApi(): Retrofit {
        val gson = GsonBuilder()
            .setLenient()
            .create()

        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(
                OkHttpClient.Builder().addInterceptor(MyInterceptor()).build()
            )
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideLoginRepository(apiService: ApiService):
            LoginRepository = LoginRepositoryImp(apiService)

    @Provides
    @Singleton
    fun provideVerifyRepository(apiService: ApiService):
            VerifyRepository = VerifyRepositoryImp(apiService)

    @Provides
    @Singleton
    fun provideCategoriesRepository(apiService: ApiService):
            CategoriesRepository = CategoriesRepositoryImp(apiService)

    @Provides
    @Singleton
    fun provideCategoryServicesRepository(apiService: ApiService):
            CategoryServicesRepository = CategoryServicesRepositoryImp(apiService)

    @Provides
    @Singleton
    fun provideLocalUserManger(
        application: Application
    ): LocalUserManger = LocalUserMangerImp(context = application)

    @Provides
    @Singleton
    fun provideAppEntryUseCase(
        localUserManger: LocalUserManger
    ) = AppEntryUseCases(
        readUserEntry = ReadUserEntry(localUserManger),
        saveUserEntry = SaveUserEntry(localUserManger),
        readToken = ReadToken(localUserManger),
        saveToken = SaveToken(localUserManger),
        readCenterId = ReadCenterId(localUserManger),
        saveCenterId = SaveCenterId(localUserManger)
    )

//    return Retrofit.Builder()
//    .baseUrl(Constants.BASE_URL)
//    .client(
//    OkHttpClient.Builder().addInterceptor { chain ->
//        chain.proceed(chain.request().newBuilder().also {
//            it.addHeader("Authorization", "Bearer ${Dimens.token}")
//        }.build())
//    }.build()
//    )
//    .addConverterFactory(GsonConverterFactory.create(gson))
//    .build()


}