package com.cipta.colorwise.ui

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.cipta.colorwise.R
import com.cipta.colorwise.data.AppDatabase
import com.cipta.colorwise.data.QuestionEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DatabaseInitializer(private val context: android.content.Context) {

    fun initializeDatabase() {
        val database = AppDatabase.getDatabase(context)
        val questionDao = database.questionDao()

        CoroutineScope(Dispatchers.IO).launch {
            // Check if the database is already populated
            if (questionDao.getRandomQuestions().isEmpty()) {
                // Define image-question-answer pairs
                val questionData = listOf(
                    Triple(R.drawable.tes1, "What number do you see?", "12"),
                    Triple(R.drawable.tes2, "Identify the number", "8"),
                    Triple(R.drawable.tes3, "What is this?", "29"),
                    Triple(R.drawable.tes4, "Number shown?", "5"),
                    Triple(R.drawable.tes5, "Recognize this", "3"),
                    Triple(R.drawable.tes6, "Can you see the number?", "15"),
                    Triple(R.drawable.tes7, "Identify the figure", "74"),
                    Triple(R.drawable.tes8, "What does this image show?", "6"),
                    Triple(R.drawable.tes9, "What is displayed?", "45"),
                    Triple(R.drawable.tes10, "Number in image?", "5"),
                    Triple(R.drawable.tes11, "What number do you see?", "27"),
                    Triple(R.drawable.tes12, "Identify the number", "16"),
                    Triple(R.drawable.tes13, "What is this?", "6"),
                    Triple(R.drawable.tes14, "Number shown?", "5"),
                    Triple(R.drawable.tes15, "Recognize this", "29"),
                    Triple(R.drawable.tes16, "Can you see the number?", "9"),
                    Triple(R.drawable.tes17, "Identify the figure", "12"),
                    Triple(R.drawable.tes18, "What does this image show?", "25"),
                    Triple(R.drawable.tes19, "What is displayed?", "2"),
                    Triple(R.drawable.tes20, "Number in image?", "18")
                )
                // Convert to database entities
                val questionList = questionData.map { (imageId, questionText, answer) ->
                    val bitmap: Bitmap = BitmapFactory.decodeResource(context.resources, imageId)
                    val byteArray: ByteArray = bitmapToByteArray(bitmap)
                    QuestionEntity(
                        id = 0,
                        image = byteArray,
                        questionText = questionText,
                        answer = answer,
                    )
                }

                // Insert into database
                questionDao.insertQuestions(questionList)
            }
        }
    }

    private fun bitmapToByteArray(bitmap: Bitmap): ByteArray {
        val stream = java.io.ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
        return stream.toByteArray()
    }
}