package com.beettechnologies.loyds.account.login.data.repository

import com.beettechnologies.loyds.account.login.data.api.LoginApi
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
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(private val loginApi: LoginApi) : LoginRepository {

    override suspend fun login(username: String, password: String): Flow<Resource<UserModel>> {
        return object : NetworkResource<UserModel, LinkedHashTreeMap<String, LinkedHashTreeMap<String, String>>>() {
            override suspend fun loadResults(item: LinkedHashTreeMap<String, LinkedHashTreeMap<String, String>>?): Flow<UserModel> {
                return flow {

                    val session = item?.get("session") as LinkedHashTreeMap<String, String>
                    val expires = session["expires"]?.toLong()

                    val isAlive = expires?.let { exp ->
                        val currentTime = Calendar.getInstance().time.time
                        val expirationTime = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).parse(convertLongToTime(exp))?.time
                        currentTime < expirationTime ?: 0
                    }?: true

                    emit(UserModel(
                        id = session["id"],
                        userId = session["userId"],
                        isAlive = isAlive,
                    ))
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

    fun convertLongToTime(time: Long): String {
        val date = Date(time * 1000)
        val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        return format.format(date)
    }
}