package com.startlearning.khabar24x7.modal.dataStore

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.userPreferencesDataStore by preferencesDataStore(name = "user_preferences")

class UserPreferencesDataStore(context: Context) {
    private val dataStore = context.userPreferencesDataStore

    val selectedLanguageFlow: Flow<String?> = dataStore.data.map { preferences ->
        preferences[KEY_SELECTED_LANGUAGE]
    }

    val selectedCategoriesFlow: Flow<Set<String>> = dataStore.data.map { preferences ->
        preferences[KEY_SELECTED_CATEGORIES] ?: emptySet()
    }

    suspend fun setSelectedLanguage(language: String) {
        dataStore.edit { preferences ->
            preferences[KEY_SELECTED_LANGUAGE] = language
        }
    }

    suspend fun toggleCategory(category: String) {
        dataStore.edit { preferences ->
            val currentCategories = preferences[KEY_SELECTED_CATEGORIES] ?: emptySet()
            val updatedCategories = if (currentCategories.contains(category)) {
                currentCategories - category
            } else {
                currentCategories + category
            }
            preferences[KEY_SELECTED_CATEGORIES] = updatedCategories
        }
    }

    companion object {
        private val KEY_SELECTED_LANGUAGE = stringPreferencesKey("selected_language")
        private val KEY_SELECTED_CATEGORIES = stringSetPreferencesKey("selected_categories")
    }
}
