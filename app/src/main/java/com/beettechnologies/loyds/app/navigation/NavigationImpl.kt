package com.beettechnologies.loyds.app.navigation

import androidx.navigation.NavController
import javax.inject.Inject

class NavigationImpl @Inject constructor() : Navigation {

    private lateinit var navController: NavController

    override fun setController(controller: NavController) {
        navController = controller
    }
}