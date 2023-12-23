package com.example.salonattask10.di

import android.util.Log
import androidx.activity.viewModels
import com.example.salonattask10.Constants
import com.example.salonattask10.MainViewModel
import com.example.salonattask10.presentation.Dimens
import com.example.salonattask10.presentation.homeScreen.HomeViewModel
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class MyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader("Authorization", "Bearer ${Constants.LOCAL_TOKEN}")
            .build()
        return chain.proceed(request)
    }
}