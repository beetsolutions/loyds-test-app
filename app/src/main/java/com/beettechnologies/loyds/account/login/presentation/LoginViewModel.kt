package com.beettechnologies.loyds.account.login.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beettechnologies.loyds.account.login.domain.interactor.LoginUseCase
import com.beettechnologies.loyds.common.data.model.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginUseCase: LoginUseCase) : ViewModel() {

    private val _isLoading: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage: MutableStateFlow<String?> = MutableStateFlow(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    private val _hasError: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val hasError: StateFlow<Boolean> = _hasError

    private val _loginSuccess: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val loginSuccess: StateFlow<Boolean> = _loginSuccess

    fun login(username: String, password: String) {
        resetUIState()
        viewModelScope.launch {
            loginUseCase(LoginUseCase.Params(username, password)).collectLatest {
                when(it.status) {
                    Status.SUCCESS -> {
                        _isLoading.value = false
                        _loginSuccess.value = true
                    }
                    Status.ERROR -> {
                        _isLoading.value = false
                        _errorMessage.value = it.message
                        _hasError.value = true
                        _loginSuccess.value = false
                    }
                    Status.LOADING -> {
                        _isLoading.value = true
                    }
                }
            }
        }
    }

    fun resetUIState() {
        _isLoading.value = true
        _hasError.value = false
        _errorMessage.value = null
        _loginSuccess.value = false
    }
}