package com.startlearning.khabar24x7.navigation

import android.preference.PreferenceDataStore
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.startlearning.khabar24x7.modal.dataStore.UserPreferencesDataStore
import com.startlearning.khabar24x7.modal.viewModal.NewsViewModel
import com.startlearning.khabar24x7.navigation.destination.homeComposable
import com.startlearning.khabar24x7.navigation.destination.loginComposable
import com.startlearning.khabar24x7.navigation.destination.newsListComposable
import com.startlearning.khabar24x7.navigation.destination.profileComposable
import com.startlearning.khabar24x7.navigation.destination.splashComposable
import com.startlearning.khabar24x7.utils.Constants.NEWS_LIST_SCREEN
import com.startlearning.khabar24x7.utils.Constants.SPLASH_SCREEN

@Composable
fun SetUpNavigation(
    navController: NavHostController,
    newsViewModel: NewsViewModel,
    userPreferencesDataStore: UserPreferencesDataStore
) {

    val screen = remember(navController) {
        Screens(navController = navController)
    }
    NavHost(
        navController = navController,
        startDestination = SPLASH_SCREEN
    ) {
        splashComposable(
            navigateToLoginScreen = screen.login
        )
        loginComposable(
            navigateToHomeScreen = screen.home,
            newsViewModel = newsViewModel
        )
        homeComposable(
            navigateToNewsListScreen = screen.newsList,
            navigateToProfileScreen=screen.profile,
            newsViewModel = newsViewModel,
            userPreferencesDataStore = userPreferencesDataStore
        )
        newsListComposable(
            navigateToMyNewsListScreen = screen.myNewsList,
            navigateToHomeScreen = screen.home,
            navigateToProfileScreen=screen.profile,
            newsViewModel = newsViewModel,
            userPreferencesDataStore = userPreferencesDataStore
        )
        profileComposable(
            navigateToMyNewsListScreen = screen.newsList,
            navigateToHomeScreen = screen.home,
            newsViewModel = newsViewModel,
            userPreferencesDataStore = userPreferencesDataStore
        )
    }
}