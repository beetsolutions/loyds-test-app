package com.beettechnologies.loyds.account.signup.data.repository

import com.beettechnologies.loyds.account.signup.data.api.SignUpApi
import com.beettechnologies.loyds.account.signup.data.model.SignUpRequest
import com.beettechnologies.loyds.account.signup.data.model.SignUpResponse
import com.beettechnologies.loyds.account.signup.domain.repository.SignUpRepository
import com.beettechnologies.loyds.common.data.model.ApiResponse
import com.beettechnologies.loyds.common.data.model.Resource
import com.beettechnologies.loyds.common.data.repository.NetworkResource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.net.UnknownHostException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SignUpRepositoryImpl @Inject constructor(private val signUpApi: SignUpApi) : SignUpRepository {

    override suspend fun signup(email: String, username: String, password: String): Flow<Resource<Boolean>> {
        return object : NetworkResource<Boolean, SignUpResponse>() {
            override suspend fun loadResults(item: SignUpResponse?): Flow<Boolean> {
                return flow {
                     emit(true)
                }
            }

            override suspend fun createCall(): ApiResponse<SignUpResponse> {
                return try {
                    val request = SignUpRequest(
                        username = username,
                        password = password,
                        email = email
                    )
                    ApiResponse.create(signUpApi.signup(request))
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