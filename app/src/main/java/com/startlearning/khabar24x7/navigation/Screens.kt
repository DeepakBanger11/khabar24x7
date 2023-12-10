package com.startlearning.khabar24x7.navigation

import androidx.navigation.NavController
import com.startlearning.khabar24x7.utils.Constants.HOME_SCREEN
import com.startlearning.khabar24x7.utils.Constants.LOGIN_SCREEN
import com.startlearning.khabar24x7.utils.Constants.NEWS_LIST_SCREEN
import com.startlearning.khabar24x7.utils.Constants.PROFILE_SCREEN
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
    val newsList :() ->Unit ={
        navController.navigate(
            route = "newsList"
        ) {
            popUpTo(HOME_SCREEN) { inclusive = true }
        }
    }
    val myNewsList :() ->Unit ={
        navController.navigate(
            route = "myNewsList"
        ) {
            popUpTo(NEWS_LIST_SCREEN) { inclusive = true }
        }
    }
    val profile :() ->Unit ={
        navController.navigate(
            route = "profile"
        ) {
            popUpTo(PROFILE_SCREEN) { inclusive = true }
        }
    }


}