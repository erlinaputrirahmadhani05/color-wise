package com.cipta.colorwise.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.cipta.colorwise.data.AppDatabase
import com.cipta.colorwise.data.HasilTes
import com.cipta.colorwise.data.UserPreferences
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import android.util.Log
import com.cipta.colorwise.data.HasilTesDao
import com.cipta.colorwise.data.User
import com.cipta.colorwise.data.UserDao
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class ColorWiseViewModel(application: Application) : AndroidViewModel(application) {

    // Bagian untuk nama pengguna
    private val userPreferences = UserPreferences(application)
    private val _userName = MutableStateFlow<String?>(null)
    val userName: StateFlow<String?> = _userName

    // DAO dan database
    private var database: AppDatabase = AppDatabase.getDatabase(application)
    private var hasilTesDao: HasilTesDao = database.hasilTesDao()
    private var userDao: UserDao = database.userDao()

    init {
        // Ambil nama pengguna dari UserPreferences dan simpan ke _userName
        viewModelScope.launch {
            userPreferences.userName.collect { name ->
                _userName.value = name
            }
        }

        // Ambil data hasil tes saat ViewModel diinisialisasi
        loadHasilTes()
    }

    // Fungsi untuk mengubah nama pengguna
    fun setUserName(name: String) {
        _userName.value = name
        saveUserName(name)
    }

    // Bagian untuk hasil tes
    private val _hasilTesList = MutableStateFlow<List<HasilTes>>(emptyList())
    val hasilTesList: StateFlow<List<HasilTes>> get() = _hasilTesList

    fun loadHasilTes() {
        viewModelScope.launch {
            try {
                _hasilTesList.value = hasilTesDao.getAllHasilTes()
            } catch (e: Exception) {
                Log.e("ColorWiseViewModel", "Error loading hasil tes: ${e.message}")
            }
        }
    }

    fun addHasilTes(hasilTes: HasilTes) {
        viewModelScope.launch {
            try {
                hasilTesDao.insertHasilTes(hasilTes)
                loadHasilTes()
            } catch (e: Exception) {
                Log.e("ColorWiseViewModel", "Error adding hasil tes: ${e.message}")
            }
        }
    }

    fun deleteHasil(id: Int) {
        viewModelScope.launch {
            try {
                hasilTesDao.deleteById(id)
                loadHasilTes()
            } catch (e: Exception) {
                Log.e("ColorWiseViewModel", "Error deleting hasil tes: ${e.message}")
            }
        }
    }

    fun clearAllHasilTes() {
        viewModelScope.launch {
            try {
                hasilTesDao.clearAll()
                loadHasilTes()
            } catch (e: Exception) {
                Log.e("ColorWiseViewModel", "Error clearing hasil tes: ${e.message}")
            }
        }
    }

    fun insertUser(userName: String) {
        viewModelScope.launch {
            userDao.insertUser(User(userName = userName))  // Memanggil insertUser dari userDao
        }
    }

    fun getAllUsers(onSuccess: (List<User>) -> Unit) {
        viewModelScope.launch {
            try {
                // Panggil userDao.getAllUsers() untuk mendapatkan data pengguna dari database
                val users = userDao.getAllUsers()  // Memanggil fungsi dari userDao
                onSuccess(users)  // Mengirim data yang berhasil diambil ke callback
            } catch (e: Exception) {
                Log.e("ColorWiseViewModel", "Error getting users: ${e.message}")
            }
        }
    }

    // Fungsi untuk menyimpan nama pengguna ke UserPreferences
    private fun saveUserName(name: String) {
        viewModelScope.launch {
            userPreferences.saveUserName(name)
        }
    }
}
