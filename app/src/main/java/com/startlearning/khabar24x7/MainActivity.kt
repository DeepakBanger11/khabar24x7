package com.startlearning.khabar24x7

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.startlearning.khabar24x7.modal.dataStore.UserPreferencesDataStore
import com.startlearning.khabar24x7.modal.viewModal.NewsViewModel
import com.startlearning.khabar24x7.navigation.SetUpNavigation
import com.startlearning.khabar24x7.ui.theme.Khabar24x7Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController
    private val newsViewModel: NewsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        val userPreferencesDataStore by lazy { UserPreferencesDataStore(this)}
        super.onCreate(savedInstanceState)
        setContent {
            Khabar24x7Theme {
                navController = rememberNavController()
                SetUpNavigation(
                    navController = navController,
                    newsViewModel = newsViewModel,
                    userPreferencesDataStore = userPreferencesDataStore
                )
            }
        }
    }
}



