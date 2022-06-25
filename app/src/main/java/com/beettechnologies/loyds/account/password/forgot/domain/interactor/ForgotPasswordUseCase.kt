package com.beettechnologies.loyds.account.password.forgot.domain.interactor

import com.beettechnologies.loyds.account.password.forgot.domain.repository.ForgotPasswordRepository
import com.beettechnologies.loyds.common.data.model.Resource
import com.beettechnologies.loyds.common.interactor.BaseUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ForgotPasswordUseCase @Inject constructor(private val repository: ForgotPasswordRepository) :
    BaseUseCase<ForgotPasswordUseCase.Params, Flow<Resource<Boolean>>>() {

    override suspend fun buildUseCase(params: Params): Flow<Resource<Boolean>> {
        return repository.resetPassword(params.email)
    }

    data class Params(val email: String)
}