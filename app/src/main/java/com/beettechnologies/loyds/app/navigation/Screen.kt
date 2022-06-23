package com.beettechnologies.loyds.app.navigation

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Register : Screen("register")
    object ForgotPassword : Screen("forgot-password")
    object Home : Screen("home")
}
