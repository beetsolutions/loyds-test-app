package com.beettechnologies.loyds.account.password.forgot.data.repository

import com.beettechnologies.loyds.account.password.forgot.data.api.ForgotPasswordApi
import com.beettechnologies.loyds.account.password.forgot.data.model.ForgotPasswordRequest
import com.beettechnologies.loyds.account.password.forgot.domain.repository.ForgotPasswordRepository
import com.beettechnologies.loyds.common.data.model.ApiResponse
import com.beettechnologies.loyds.common.data.model.Resource
import com.beettechnologies.loyds.common.data.repository.NetworkResource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.net.UnknownHostException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ForgotPasswordRepositoryImpl @Inject constructor(private val forgotPasswordApi: ForgotPasswordApi) :
    ForgotPasswordRepository {

    override suspend fun resetPassword(email: String): Flow<Resource<Boolean>> {
        return object : NetworkResource<Boolean, Any>() {
            override suspend fun loadResults(item: Any?): Flow<Boolean> {
                return flow {
                    emit(true)
                }
            }

            override suspend fun createCall(): ApiResponse<Any> {
                return try {
                    val request = ForgotPasswordRequest(email = email)
                    ApiResponse.create(forgotPasswordApi.resetPassword(request))
                } catch (throwable: Throwable) {
                    val error = if (throwable is UnknownHostException) {
                        Throwable(message = "No internet connection available!")
                    } else throwable
                    ApiResponse.create(error)
                }
            }
        }.asFlow()
    }
}