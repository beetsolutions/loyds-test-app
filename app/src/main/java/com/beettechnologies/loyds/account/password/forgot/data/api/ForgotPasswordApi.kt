package com.beettechnologies.loyds.account.password.forgot.data.api

import com.beettechnologies.loyds.account.password.forgot.data.model.ForgotPasswordRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ForgotPasswordApi {
    @POST("/v1/user/SendPasswordResetEmail")
    suspend fun resetPassword(@Body request: ForgotPasswordRequest): Response<Any>
}