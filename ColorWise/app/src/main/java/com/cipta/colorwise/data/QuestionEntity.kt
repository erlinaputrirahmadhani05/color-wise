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