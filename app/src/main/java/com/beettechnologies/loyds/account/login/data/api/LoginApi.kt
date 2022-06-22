package com.beettechnologies.loyds.account.login.data.api

import com.beettechnologies.loyds.account.login.data.model.LoginRequest
import com.google.gson.internal.LinkedHashTreeMap
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApi {
    @POST("/v1/user/Login")
    suspend fun login(@Body request: LoginRequest): Response<LinkedHashTreeMap<String, LinkedHashTreeMap<String, String>>>
}