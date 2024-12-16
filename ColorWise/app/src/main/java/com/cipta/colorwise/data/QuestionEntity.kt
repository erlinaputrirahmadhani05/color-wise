package com.cipta.colorwise.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "questions")
data class QuestionEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val image: ByteArray,
    val questionText: String,
    val answer: String
)

@Entity(tableName = "hasil_tes")
data class HasilTes(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val totalPercobaan: Int,
    val benar: Int,
    val salah: Int,
    val timestamp: Long = System.currentTimeMillis()
)

@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int = 0, // Primary key otomatis
    val userName: String
)