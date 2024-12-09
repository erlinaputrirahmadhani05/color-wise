package com.cipta.colorwise.data

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// DataStore instance
val Context.dataStore by preferencesDataStore("user_prefs")

class UserPreferences(private val context: Context) {


    // Key untuk nama pengguna
    companion object {
        private val USER_NAME_KEY = stringPreferencesKey("user_name")
    }

    // Fungsi untuk menyimpan nama pengguna
    suspend fun saveUserName(name: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_NAME_KEY] = name
        }
    }

    // Fungsi untuk mengambil nama pengguna (Flow)
    val userName: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[USER_NAME_KEY]
        }
}