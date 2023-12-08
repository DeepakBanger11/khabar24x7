package com.startlearning.khabar24x7

import android.app.Application
import com.startlearning.khabar24x7.modal.dataStore.UserPreferencesDataStore
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class Khabar24x7Application :Application(){
    val userPreferencesDataStore by lazy { UserPreferencesDataStore(this) }

}