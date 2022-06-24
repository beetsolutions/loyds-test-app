package com.beettechnologies.loyds.app.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.beettechnologies.loyds.account.login.presentation.LoginView
import com.beettechnologies.loyds.account.password.forgot.presentation.ForgotPasswordView
import com.beettechnologies.loyds.account.signup.presentation.SignUpView
import com.beettechnologies.loyds.home.presentation.HomeView

@ExperimentalAnimationApi
@Composable
fun NavigationHost(navController: NavHostController, navigation: Navigation) {
    NavHost(
        navController = navController,
        startDestination = Screen.Login.route
    ) {
        composable(route = Screen.Login.route) {
            LoginView(navigation = navigation)
        }
        composable(route = Screen.Home.route) {
            HomeView(navigation = navigation)
        }
        composable(route = Screen.Register.route) {
            SignUpView(navigation = navigation)
        }
        composable(route = Screen.ForgotPassword.route) {
            ForgotPasswordView(navigation = navigation)
        }
    }
}
