package com.beettechnologies.loyds.account.signup.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beettechnologies.loyds.account.signup.domain.interactor.SignUpUseCase
import com.beettechnologies.loyds.common.data.model.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(private val signUpUseCase: SignUpUseCase) : ViewModel() {

    private val _isLoading: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage: MutableStateFlow<String?> = MutableStateFlow(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    private val _hasError: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val hasError: StateFlow<Boolean> = _hasError

    private val _isSuccessful: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isSuccessful: StateFlow<Boolean> = _isSuccessful

    fun signup(email: String, username: String, password: String) {
        resetUIState()
        viewModelScope.launch {
            signUpUseCase(SignUpUseCase.Params(email, username, password)).collectLatest {
                when(it.status) {
                    Status.SUCCESS -> {
                        _isLoading.value = false
                        _isSuccessful.value = true
                    }
                    Status.ERROR -> {
                        _isLoading.value = false
                        _errorMessage.value = it.message
                        _hasError.value = true
                        _isSuccessful.value = false
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
        _isSuccessful.value = false
        _errorMessage.value = null
    }
}