package com.beettechnologies.loyds.account.signup.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beettechnologies.loyds.account.signup.domain.interactor.SignUpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(private val signUpUseCase: SignUpUseCase) : ViewModel() {

    val isLoading: MutableStateFlow<Boolean> = MutableStateFlow(false)

    fun signup(email: String, username: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            signUpUseCase.signup(email, username, password).collectLatest {

            }
        }
    }
}