package com.startlearning.khabar24x7.navigation.destination

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideOutVertically
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.startlearning.khabar24x7.modal.dataStore.UserPreferencesDataStore
import com.startlearning.khabar24x7.modal.viewModal.NewsViewModel
import com.startlearning.khabar24x7.ui.screens.home.ProfileScreen
import com.startlearning.khabar24x7.ui.screens.news.NewsListScreen
import com.startlearning.khabar24x7.utils.Constants

fun NavGraphBuilder.profileComposable(
    navigateToMyNewsListScreen: () -> Unit,
    navigateToHomeScreen: () -> Unit,
    newsViewModel: NewsViewModel,
    userPreferencesDataStore: UserPreferencesDataStore
) {

    composable(
        route = Constants.PROFILE_SCREEN,
        exitTransition = {
            slideOutVertically(
                animationSpec = tween(200),
                targetOffsetY = { fullHeight -> -fullHeight }
            )
        }
    )
    {
        ProfileScreen(
            navigateToMyNewsListScreen = navigateToMyNewsListScreen,
            navigateToHomeScreen = navigateToHomeScreen,
            newsViewModel = newsViewModel,
            userPreferencesDataStore = userPreferencesDataStore
        )
    }
}