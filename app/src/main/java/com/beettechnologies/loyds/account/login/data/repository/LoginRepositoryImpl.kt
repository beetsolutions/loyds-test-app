package com.beettechnologies.loyds.account.login.data.repository

import com.beettechnologies.loyds.account.login.data.api.LoginApi
import com.beettechnologies.loyds.account.login.data.model.LoginRequest
import com.beettechnologies.loyds.account.login.data.model.LoginResponse
import com.beettechnologies.loyds.account.login.domain.repository.LoginRepository
import com.beettechnologies.loyds.account.signup.domain.model.UserModel
import com.beettechnologies.loyds.common.data.model.ApiResponse
import com.beettechnologies.loyds.common.data.model.Resource
import com.beettechnologies.loyds.common.data.repository.NetworkResource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.net.UnknownHostException
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(private val loginApi: LoginApi) : LoginRepository {

    override suspend fun login(username: String, password: String): Flow<Resource<UserModel>> {
        return object : NetworkResource<UserModel, LoginResponse>() {
            override suspend fun loadResults(item: LoginResponse?): Flow<UserModel> {
                return flow {
                    UserModel()
                }
            }

            override suspend fun createCall(): ApiResponse<LoginResponse> {
                return try {
                    val request = LoginRequest(
                        username = username,
                        password = password
                    )
                    ApiResponse.create(loginApi.login(request))
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