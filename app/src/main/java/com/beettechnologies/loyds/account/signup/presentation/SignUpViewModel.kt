package com.beettechnologies.loyds.account.signup.presentation

import androidx.lifecycle.ViewModel
import com.beettechnologies.loyds.account.signup.domain.interactor.SignUpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(private val signUpUseCase: SignUpUseCase) : ViewModel()