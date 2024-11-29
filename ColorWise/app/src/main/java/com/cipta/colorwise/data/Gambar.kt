package com.cipta.colorwise.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "gambar")
data class Gambar(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val nama: String,
    val url: String
)