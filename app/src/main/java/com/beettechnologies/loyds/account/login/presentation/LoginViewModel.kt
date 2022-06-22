package com.beettechnologies.loyds.account.login.presentation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {

    val isLoading: MutableStateFlow<Boolean> = MutableStateFlow(false)
}