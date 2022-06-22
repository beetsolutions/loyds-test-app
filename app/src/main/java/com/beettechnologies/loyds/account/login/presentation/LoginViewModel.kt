package com.beettechnologies.loyds.account.login.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beettechnologies.loyds.account.login.domain.interactor.LoginUseCase
import com.beettechnologies.loyds.common.data.model.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginUseCase: LoginUseCase) : ViewModel() {

    val isLoading: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val errorMessage: MutableStateFlow<String?> = MutableStateFlow(null)
    val hasError: MutableStateFlow<Boolean> = MutableStateFlow(false)

    fun login(username: String, password: String) {
        isLoading.value = true
        viewModelScope.launch {
            loginUseCase(LoginUseCase.Params(username, password)).collectLatest {
                when(it.status) {
                    Status.SUCCESS -> {
                        isLoading.value = false
                    }
                    Status.ERROR -> {
                        isLoading.value = false
                        errorMessage.value = it.message
                        hasError.value = true
                    }
                    Status.LOADING -> {
                        isLoading.value = true
                    }
                }
            }
        }
    }
}