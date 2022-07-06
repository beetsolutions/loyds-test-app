package com.beettechnologies.loyds.common.interceptor

import com.beettechnologies.loyds.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class ApiTokenInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val url = request.url.newBuilder()
            .build()

        val newRequest = request.newBuilder()
            .url(url)
            .addHeader(CONTENT_TYPE, "application/json")
            .addHeader(AUTHORIZATION, "$BEARER ${BuildConfig.API_KEY}")
        return chain.proceed(newRequest.build())
    }

    companion object {
        const val CONTENT_TYPE = "Content-Type"
        const val AUTHORIZATION = "Authorization"
        const val BEARER = "Bearer"
    }
}