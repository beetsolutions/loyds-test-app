package com.beettechnologies.loyds.account.signup.data.api

import com.beettechnologies.loyds.account.signup.data.model.SignUpRequest
import com.beettechnologies.loyds.account.signup.data.model.SignUpResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface SignUpApi {
    @POST("/v1/user/Create")
    suspend fun signup(@Body request: SignUpRequest): Response<SignUpResponse>
}