package com.beettechnologies.loyds.common.interceptor

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
            .addHeader(AUTHORIZATION, "$BEARER $API_KEY")
        return chain.proceed(newRequest.build())
    }

    companion object {
        const val CONTENT_TYPE = "Content-Type"
        const val AUTHORIZATION = "Authorization"
        const val BEARER = "Bearer"
        const val API_KEY = "ZGE0YzAxYWUtNTI1MC00N2IzLWEyMWItM2IyMzI1NzUwODI4" // TODO Move to CI env for production usage.
    }
}