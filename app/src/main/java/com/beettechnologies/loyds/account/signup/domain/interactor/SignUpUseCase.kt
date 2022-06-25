package com.beettechnologies.loyds.account.signup.domain.interactor

import com.beettechnologies.loyds.account.signup.domain.repository.SignUpRepository
import com.beettechnologies.loyds.common.data.model.Resource
import com.beettechnologies.loyds.common.interactor.BaseUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SignUpUseCase @Inject constructor(private val repository: SignUpRepository) : BaseUseCase<SignUpUseCase.Params, Flow<Resource<Boolean>>>() {

    override suspend fun buildUseCase(params: Params): Flow<Resource<Boolean>> {
        return repository.signup(params.email, params.username, params.password)
    }

    data class Params(val email: String, val username: String, val password: String)
}