package com.startlearning.khabar24x7.modal.dataStore

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private val Context.userPreferencesDataStore by preferencesDataStore(name = "user_preferences")

@Singleton
class UserPreferencesDataStore @Inject constructor(
    @ApplicationContext private val context: Context) {
    private val dataStore = context.userPreferencesDataStore

    val selectedLanguageFlow: Flow<String?> = dataStore.data.map { preferences ->
        preferences[KEY_SELECTED_LANGUAGE]
    }

    val selectedCategoriesFlow: Flow<String?> = dataStore.data.map { preferences ->
        preferences[KEY_SELECTED_CATEGORIES]
    }

    suspend fun setSelectedLanguage(language: String) {
        dataStore.edit { preferences ->
            preferences[KEY_SELECTED_LANGUAGE] = language
        }
    }

    suspend fun toggleCategory(category: String) {
        dataStore.edit { preferences ->
            preferences[KEY_SELECTED_CATEGORIES] = category
        }
    }

    companion object {
        private val KEY_SELECTED_LANGUAGE = stringPreferencesKey("selected_language")
        private val KEY_SELECTED_CATEGORIES = stringPreferencesKey("selected_categories")
    }
}
