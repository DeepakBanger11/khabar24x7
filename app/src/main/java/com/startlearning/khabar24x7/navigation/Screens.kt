package com.startlearning.khabar24x7.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import com.startlearning.khabar24x7.modal.dataStore.UserPreferencesDataStore
import com.startlearning.khabar24x7.utils.Constants.HOME_SCREEN
import com.startlearning.khabar24x7.utils.Constants.LOGIN_SCREEN
import com.startlearning.khabar24x7.utils.Constants.NEWS_DETAIL_SCREEN
import com.startlearning.khabar24x7.utils.Constants.NEWS_LIST_SCREEN
import com.startlearning.khabar24x7.utils.Constants.PROFILE_SCREEN
import com.startlearning.khabar24x7.utils.Constants.SPLASH_SCREEN

class Screens(
    navController: NavController,
    navigation: String
) {
    val splash: () -> Unit = {
        navController.navigate(
            route =
                when(navigation){
                    "home" ->"home"
                    else ->"login"
                }
        ) {
            popUpTo(SPLASH_SCREEN) { inclusive = true }
        }
    }

    val login :() ->Unit ={
        navController.navigate(
            route = "home"
        ) {
            popUpTo(LOGIN_SCREEN) { inclusive = true }
        }
    }
    val home :() ->Unit ={
        navController.navigate(
            route = "home"
        ) {
            popUpTo(HOME_SCREEN) { inclusive = true }
        }
    }
    val newsList :() ->Unit ={
        navController.navigate(
            route = "newsList"
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
    val newsDetails :() ->Unit ={
        navController.navigate(
           route = "newsDetails"
        ) {
            popUpTo(NEWS_DETAIL_SCREEN) { inclusive = true }
        }
    }


}