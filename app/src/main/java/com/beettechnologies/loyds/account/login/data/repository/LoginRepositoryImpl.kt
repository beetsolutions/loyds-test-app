package com.beettechnologies.loyds.account.login.data.repository

import com.beettechnologies.loyds.account.login.data.api.LoginApi
import com.beettechnologies.loyds.account.login.data.mapper.UserMapper
import com.beettechnologies.loyds.account.login.data.model.LoginRequest
import com.beettechnologies.loyds.account.login.domain.repository.LoginRepository
import com.beettechnologies.loyds.account.signup.domain.model.UserModel
import com.beettechnologies.loyds.common.data.model.ApiResponse
import com.beettechnologies.loyds.common.data.model.Resource
import com.beettechnologies.loyds.common.data.repository.NetworkResource
import com.google.gson.internal.LinkedHashTreeMap
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.net.UnknownHostException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginRepositoryImpl @Inject constructor(private val loginApi: LoginApi, private val mapper: UserMapper) : LoginRepository {

    override suspend fun login(username: String, password: String): Flow<Resource<UserModel>> {
        return object : NetworkResource<UserModel, LinkedHashTreeMap<String, LinkedHashTreeMap<String, String>>>() {
            override suspend fun loadResults(item: LinkedHashTreeMap<String, LinkedHashTreeMap<String, String>>?): Flow<UserModel> {
                return flow {
                    emit(mapper.map(item))
                }
            }

            override suspend fun createCall(): ApiResponse<LinkedHashTreeMap<String, LinkedHashTreeMap<String, String>>> {
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