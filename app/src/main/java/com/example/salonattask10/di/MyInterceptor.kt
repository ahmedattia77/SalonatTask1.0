package com.example.salonattask10.di

import com.example.salonattask10.presentation.Dimens
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class MyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader("Authorization", "Bearer ${Dimens.token}")
            .build()
        return chain.proceed(request)
    }
}