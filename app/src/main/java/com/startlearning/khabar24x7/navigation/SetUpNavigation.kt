package com.startlearning.khabar24x7.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.startlearning.khabar24x7.navigation.destination.loginComposable
import com.startlearning.khabar24x7.navigation.destination.splashComposable
import com.startlearning.khabar24x7.utils.Constants.SPLASH_SCREEN

@Composable
fun SetUpNavigation(navController: NavHostController) {

    val screen = remember(navController) {
        Screens(navController = navController)
    }
    NavHost(
        navController = navController,
        startDestination = SPLASH_SCREEN
    ){
        splashComposable(
            navigateToLoginScreen = screen.login
        )
        loginComposable(
            navigateToHomeScreen = screen.home
        )

    }
}