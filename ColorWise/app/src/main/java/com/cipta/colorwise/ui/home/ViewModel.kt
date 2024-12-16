package com.cipta.colorwise.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.cipta.colorwise.data.AppDatabase
import com.cipta.colorwise.data.HasilTes
import com.cipta.colorwise.data.HasilTesDao
import com.cipta.colorwise.data.User
import com.cipta.colorwise.data.UserDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import android.util.Log

class ColorWiseViewModel(application: Application) : AndroidViewModel(application) {

    // Bagian untuk nama pengguna
    private val _userName = MutableStateFlow<String?>("User belum diinput")
    val userName: StateFlow<String?> = _userName

    // DAO dan database
    private val database: AppDatabase = AppDatabase.getDatabase(application)
    private val hasilTesDao: HasilTesDao = database.hasilTesDao()
    private val userDao: UserDao = database.userDao()

    init {
        loadLastUser()
        loadHasilTes()
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

    // Tambahkan user baru dan langsung perbarui _userName
    fun insertUser(userName: String) {
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    userDao.insertUser(User(userName = userName))
                }
                _userName.value = userName // Perbarui stateFlow dengan nama user baru
            } catch (e: Exception) {
                Log.e("ColorWiseViewModel", "Error inserting user: ${e.message}")
            }
        }
    }

    // Ambil semua user dari database
    fun getAllUsers(onSuccess: (List<User>) -> Unit) {
        viewModelScope.launch {
            try {
                val users = withContext(Dispatchers.IO) {
                    userDao.getAllUsers()
                }
                onSuccess(users)
            } catch (e: Exception) {
                Log.e("ColorWiseViewModel", "Error getting users: ${e.message}")
            }
        }
    }

    // Ambil user terakhir dari database
    private fun loadLastUser() {
        viewModelScope.launch {
            try {
                val lastUser = withContext(Dispatchers.IO) { userDao.getLastUser() }
                _userName.value = lastUser?.userName ?: "User belum diinput"
            } catch (e: Exception) {
                Log.e("ColorWiseViewModel", "Error loading last user: ${e.message}")
            }
        }
    }

    suspend fun getLastUser(): User? {
        return withContext(Dispatchers.IO) {
            userDao.getLastUser()
        }
    }

}
