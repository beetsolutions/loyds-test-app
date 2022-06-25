package com.beettechnologies.loyds.account.password.forgot.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beettechnologies.loyds.account.password.forgot.domain.interactor.ForgotPasswordUseCase
import com.beettechnologies.loyds.common.data.model.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForgotPasswordViewModel @Inject constructor(private val forgotPasswordUseCase: ForgotPasswordUseCase) :
    ViewModel() {

    val isLoading: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val errorMessage: MutableStateFlow<String?> = MutableStateFlow(null)
    val hasError: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isSuccessful: MutableStateFlow<Boolean> = MutableStateFlow(false)

    fun resetPassword(email: String) {
        resetUIState()
        viewModelScope.launch {
            forgotPasswordUseCase(ForgotPasswordUseCase.Params(email)).collectLatest {
                when (it.status) {
                    Status.SUCCESS -> {
                        isLoading.value = false
                        isSuccessful.value = true
                    }
                    Status.ERROR -> {
                        isLoading.value = false
                        errorMessage.value = it.message
                        hasError.value = true
                        isSuccessful.value = false
                    }
                    Status.LOADING -> {
                        isLoading.value = true
                    }
                }
            }
        }
    }

    fun resetUIState() {
        isLoading.value = true
        hasError.value = false
        hasError.value = false
        isSuccessful.value = false
    }
}