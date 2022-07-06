package com.beettechnologies.loyds.app.navigation

import androidx.navigation.NavController
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NavigationImpl @Inject constructor() : Navigation {

    private lateinit var navController: NavController

    override fun setController(controller: NavController) {
        navController = controller
    }

    override fun navigateToHome() {
        navController.popBackStack(Screen.Login.route, true)
        navController.navigate(Screen.Home.route) {
           launchSingleTop = true
        }
    }

    override fun navigateToRegistration() {
        navController.navigate(Screen.Register.route) {
            restoreState = true
        }
    }

    override fun navigateToForgotPassword() {
        navController.navigate(Screen.ForgotPassword.route) {
            restoreState = true
        }
    }

    override fun navigateToLogin() {
        navController.navigate(Screen.Login.route)
    }

    override fun navigateBack() {
        navController.popBackStack()
    }
}