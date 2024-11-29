package com.cipta.colorwise.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.cipta.colorwise.data.UserPreferences
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ColorWiseViewModel(application: Application) : AndroidViewModel(application) {

    private val userPreferences = UserPreferences(application)

    // StateFlow untuk nama pengguna
    private val _userName = MutableStateFlow<String?>(null)
    val userName: StateFlow<String?> get() = _userName

    init {
        // Ambil nama pengguna saat ViewModel diinisialisasi
        viewModelScope.launch {
            userPreferences.userName.collect { name ->
                _userName.value = name
            }
        }
    }
    fun setUserName(name: String) {
        _userName.value = name
    }
}