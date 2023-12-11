package com.startlearning.khabar24x7.navigation.destination

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideOutVertically
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.startlearning.khabar24x7.modal.viewModal.NewsViewModel
import com.startlearning.khabar24x7.ui.screens.LoginScreen
import com.startlearning.khabar24x7.ui.screens.SplashScreen
import com.startlearning.khabar24x7.utils.Constants

fun NavGraphBuilder.loginComposable(
    navigateToSelectedScreen: () -> Unit,
    newsViewModel: NewsViewModel
) {

    composable(
        route = Constants.LOGIN_SCREEN,
        exitTransition = {
            slideOutVertically(
                animationSpec = tween(200),
                targetOffsetY = { fullHeight -> -fullHeight }
            )
        }
    )
    {
        LoginScreen(
            navigateToSelectedScreen = navigateToSelectedScreen,
            newsViewModel = newsViewModel
        )
    }
}
