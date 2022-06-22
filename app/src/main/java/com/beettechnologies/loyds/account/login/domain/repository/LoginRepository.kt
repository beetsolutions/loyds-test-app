package com.beettechnologies.loyds.account.login.domain.repository

import com.beettechnologies.loyds.account.signup.domain.model.UserModel
import com.beettechnologies.loyds.common.data.model.Resource
import kotlinx.coroutines.flow.Flow

interface LoginRepository {
    suspend fun login(username: String, password: String) : Flow<Resource<UserModel>>
}