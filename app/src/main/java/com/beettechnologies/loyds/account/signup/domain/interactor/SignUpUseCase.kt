package com.beettechnologies.loyds.account.signup.domain.interactor

import com.beettechnologies.loyds.account.signup.domain.repository.SignUpRepository
import javax.inject.Inject

class SignUpUseCase @Inject constructor(private val repository: SignUpRepository)