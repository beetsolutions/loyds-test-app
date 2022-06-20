package com.beettechnologies.loyds.account.signup.domain.repository

import com.beettechnologies.loyds.account.signup.domain.model.UserModel
import com.beettechnologies.loyds.common.data.model.Resource
import kotlinx.coroutines.flow.Flow

interface SignUpRepository {
    suspend fun signup(username: String, password: String, email: String): Flow<Resource<UserModel>>
}