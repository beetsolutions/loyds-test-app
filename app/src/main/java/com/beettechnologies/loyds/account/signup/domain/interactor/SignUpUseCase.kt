package com.beettechnologies.loyds.account.signup.domain.interactor

import com.beettechnologies.loyds.account.signup.domain.repository.SignUpRepository
import javax.inject.Inject

class SignUpUseCase @Inject constructor(private val repository: SignUpRepository) {
    suspend fun signup(email: String, username: String, password: String) = repository.signup(email, username, password)
}