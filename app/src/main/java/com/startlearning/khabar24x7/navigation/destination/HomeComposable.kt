package com.startlearning.khabar24x7.navigation.destination

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideOutVertically
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.startlearning.khabar24x7.modal.dataStore.UserPreferencesDataStore

import com.startlearning.khabar24x7.ui.screens.home.HomeScreen
import com.startlearning.khabar24x7.utils.Constants

fun NavGraphBuilder.homeComposable(
    navigateToNewsListScreen: () -> Unit,
    userPreferencesDataStore: UserPreferencesDataStore
) {

    composable(
        route = Constants.HOME_SCREEN,
        exitTransition = {
            slideOutVertically(
                animationSpec = tween(200),
                targetOffsetY = { fullHeight -> -fullHeight }
            )
        }
    )
    {
        HomeScreen(
            navigateToNewsListScreen = navigateToNewsListScreen,
            userPreferencesDataStore = userPreferencesDataStore
        )
    }
}