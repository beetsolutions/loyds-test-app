package com.beettechnologies.loyds.account.login.domain.interactor

import com.beettechnologies.loyds.account.login.domain.repository.LoginRepository
import com.beettechnologies.loyds.account.signup.domain.model.UserModel
import com.beettechnologies.loyds.common.data.model.Resource
import com.beettechnologies.loyds.common.interactor.BaseUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val repository: LoginRepository) : BaseUseCase<LoginUseCase.Params, Flow<Resource<UserModel>>>() {

    override suspend fun buildUseCase(params: Params): Flow<Resource<UserModel>> {
        return repository.login(params.username, params.password)
    }

    data class Params(val username: String, val password: String)
}