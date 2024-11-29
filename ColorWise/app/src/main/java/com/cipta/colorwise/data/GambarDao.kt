package com.cipta.colorwise.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface GambarDao {
    @Insert
    suspend fun insert(gambar: Gambar)

    @Query("SELECT * FROM gambar")
    suspend fun getAllGambar (gambar: Gambar)
}