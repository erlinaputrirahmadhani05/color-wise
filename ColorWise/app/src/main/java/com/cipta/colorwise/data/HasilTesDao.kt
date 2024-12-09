package com.cipta.colorwise.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface HasilTesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHasilTes(hasilTes: HasilTes)

    @Query("SELECT * FROM hasil_tes ORDER BY timestamp DESC")
    suspend fun getAllHasilTes(): List<HasilTes>

    @Query("DELETE FROM hasil_tes WHERE id = :id")
    suspend fun deleteById(id: Int)

    @Query("DELETE FROM hasil_tes")
    suspend fun clearAll()
}