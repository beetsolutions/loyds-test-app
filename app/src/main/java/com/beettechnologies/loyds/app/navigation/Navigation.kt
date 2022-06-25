package com.beettechnologies.loyds.app.navigation

import androidx.navigation.NavController

interface Navigation {
    fun setController(controller: NavController)
    fun navigateToHome()
    fun navigateToRegistration()
    fun navigateToForgotPassword()
    fun navigateToLogin()
    fun navigateBack()
}