package com.beettechnologies.loyds.account.login.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beettechnologies.loyds.account.login.domain.interactor.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginUseCase: LoginUseCase) : ViewModel() {

    val isLoading: MutableStateFlow<Boolean> = MutableStateFlow(false)

    fun login(username: String, password: String) {
        isLoading.value = true
        viewModelScope.launch {
            loginUseCase.login(username, password).collectLatest {
                println(it)
            }
        }
    }
}