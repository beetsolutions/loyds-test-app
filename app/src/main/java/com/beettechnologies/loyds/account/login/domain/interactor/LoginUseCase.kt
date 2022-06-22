package com.beettechnologies.loyds.account.login.domain.interactor

import com.beettechnologies.loyds.account.login.domain.repository.LoginRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val repository: LoginRepository) {
    suspend fun login(username: String, password: String) = repository.login(username, password)
}