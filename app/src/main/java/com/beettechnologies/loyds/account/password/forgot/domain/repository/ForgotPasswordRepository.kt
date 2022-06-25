package com.beettechnologies.loyds.account.password.forgot.domain.repository

import com.beettechnologies.loyds.common.data.model.Resource
import kotlinx.coroutines.flow.Flow

interface ForgotPasswordRepository {
    suspend fun resetPassword(email: String): Flow<Resource<Boolean>>
}