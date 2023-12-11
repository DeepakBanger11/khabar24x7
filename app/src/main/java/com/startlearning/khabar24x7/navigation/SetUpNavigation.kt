package com.startlearning.khabar24x7.navigation


import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.startlearning.khabar24x7.modal.dataStore.UserPreferencesDataStore
import com.startlearning.khabar24x7.modal.viewModal.NewsViewModel
import com.startlearning.khabar24x7.navigation.destination.homeComposable
import com.startlearning.khabar24x7.navigation.destination.loginComposable
import com.startlearning.khabar24x7.navigation.destination.newsDetailComposable
import com.startlearning.khabar24x7.navigation.destination.newsListComposable
import com.startlearning.khabar24x7.navigation.destination.profileComposable
import com.startlearning.khabar24x7.navigation.destination.splashComposable
import com.startlearning.khabar24x7.utils.Constants.SPLASH_SCREEN

@Composable
fun SetUpNavigation(
    navController: NavHostController,
    newsViewModel: NewsViewModel,
    userPreferencesDataStore: UserPreferencesDataStore
) {
    val navigation by userPreferencesDataStore.navigation.collectAsState(initial = "home")
    Log.d("navi", "$navigation")
    val screen = remember(navController) {
        navigation?.let {
            Screens(
                navController = navController,
                navigation = it
            )
        }
    }
    NavHost(
        navController = navController,
        startDestination = SPLASH_SCREEN
    ) {
        splashComposable(
            navigateToSelectedScreen = screen!!.splash
        )
        loginComposable(
            navigateToSelectedScreen = screen.login,
            newsViewModel = newsViewModel
        )
        homeComposable(
            navController = navController,
            newsViewModel = newsViewModel,
            userPreferencesDataStore = userPreferencesDataStore
        )
        newsListComposable(
            navController = navController,
            newsViewModel = newsViewModel,
            userPreferencesDataStore = userPreferencesDataStore
        )
        profileComposable(
            navController = navController,
            newsViewModel = newsViewModel,
            userPreferencesDataStore = userPreferencesDataStore
        )
        newsDetailComposable(
            navController = navController,
            newsViewModel = newsViewModel,
            userPreferencesDataStore = userPreferencesDataStore
        )
    }
}