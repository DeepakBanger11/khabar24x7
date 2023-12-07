package com.startlearning.khabar24x7.navigation

import androidx.navigation.NavController
import com.startlearning.khabar24x7.utils.Constants.LOGIN_SCREEN
import com.startlearning.khabar24x7.utils.Constants.SPLASH_SCREEN

class Screens(navController: NavController) {
    val login: () -> Unit = {
        navController.navigate("login") {
            popUpTo(SPLASH_SCREEN) { inclusive = true }
        }
    }

    val home :() ->Unit ={
        navController.navigate(
            route = "home"
        ) {
            popUpTo(LOGIN_SCREEN) { inclusive = true }
        }
    }


}