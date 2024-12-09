package com.cipta.colorwise.data

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface QuestionDao {
    // Query untuk mengambil data secara acak dengan paging
    @Query("SELECT * FROM questions ORDER BY RANDOM()")
    fun getPagedQuestions(): PagingSource<Int, QuestionEntity>

    // Query untuk mendapatkan ID soal secara acak (jika diperlukan tanpa paging)
    @Query("SELECT id FROM questions ORDER BY RANDOM() LIMIT 10")
    suspend fun getRandomQuestions(): List<Int>

    // Query untuk mendapatkan soal berdasarkan ID
    @Query("SELECT * FROM questions WHERE id = :id LIMIT 1")
    suspend fun getQuestionById(id: Int): QuestionEntity

    // Query untuk menyisipkan data baru
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuestions(questions: List<QuestionEntity>)
}
