package com.startlearning.khabar24x7.navigation.destination

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideOutVertically
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.startlearning.khabar24x7.modal.dataStore.UserPreferencesDataStore
import com.startlearning.khabar24x7.modal.viewModal.NewsViewModel
import com.startlearning.khabar24x7.ui.screens.news.NewsArticleScreen
import com.startlearning.khabar24x7.utils.Constants

fun NavGraphBuilder.newsArticleComposable(
    navController: NavHostController,
    newsViewModel: NewsViewModel,
    userPreferencesDataStore: UserPreferencesDataStore
) {

    composable(
        route = Constants.NEWS_ARTICLE_SCREEN,
        exitTransition = {
            slideOutVertically(
                animationSpec = tween(200),
                targetOffsetY = { fullHeight -> -fullHeight }
            )
        }
    )
    {
        NewsArticleScreen(
            navController = navController,
            newsViewModel = newsViewModel,
            userPreferencesDataStore = userPreferencesDataStore

        )
    }
}