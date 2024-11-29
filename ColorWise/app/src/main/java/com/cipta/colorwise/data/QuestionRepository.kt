package com.cipta.colorwise.data

import android.content.Context
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class QuestionRepository(private val context: Context) {
    private val questionDao = AppDatabase.getDatabase(context).questionDao()

    // Fetch data secara acak dan paged
    fun getPagedQuestions(pageSize: Int = 5): Flow<PagingData<QuestionEntity>> {
        return Pager(
            config = PagingConfig(pageSize = pageSize, enablePlaceholders = false),
            pagingSourceFactory = { questionDao.getPagedQuestions() }
        ).flow
    }

    // Fetch dan shuffle data (non-paged, jika masih dibutuhkan)
    suspend fun fetchAndShuffleQuestions(): List<Int> {
        return withContext(Dispatchers.IO) {
            questionDao.getRandomQuestions().shuffled() // Ambil dan acak ID
        }
    }

    // Mendapatkan pertanyaan berdasarkan ID
    suspend fun getQuestionById(id: Int): QuestionEntity {
        return withContext(Dispatchers.IO) {
            questionDao.getQuestionById(id)
        }
    }
}
