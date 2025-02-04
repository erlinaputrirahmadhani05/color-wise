package com.cipta.colorwise.ui.test

import android.content.Context
import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.cipta.colorwise.R
import com.cipta.colorwise.data.QuestionEntity
import com.cipta.colorwise.data.QuestionRepository
import kotlinx.coroutines.launch
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun TestScreen(
    context: Context,
    navController: NavController,
    totalQuestions: Int = 10,
    correctAnswer: String
) {
    val repository = remember { QuestionRepository(context) }
    var questionIds by remember { mutableStateOf<List<Int>>(emptyList()) }
    var currentIndex by remember { mutableStateOf(0) }
    var currentQuestion by remember { mutableStateOf<QuestionEntity?>(null) }
    var answer by remember { mutableStateOf(TextFieldValue("")) }
    var correctAnswers by remember { mutableStateOf(0) }
    var errorMessage by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()

    // Memuat soal saat UI pertama kali dibuka
    LaunchedEffect(Unit) {
        questionIds = repository.fetchAndShuffleQuestions()
        if (questionIds.isNotEmpty()) {
            currentQuestion = repository.getQuestionById(questionIds[currentIndex])
        }
    }

    // Menampilkan loading jika soal belum siap
    if (questionIds.isEmpty() || currentQuestion == null) {
        Text(
            text = "Loading questions...",
            fontSize = 18.sp,
            color = Color.White,
            modifier = Modifier.fillMaxSize(),
        )
        return
    }

    // Fungsi validasi
    fun isValidNumber(input: String): Boolean = input.all { it.isDigit() }
    fun onAnswerChanged(newValue: TextFieldValue) {
        val newText = newValue.text
        if (isValidNumber(newText) || newText.isEmpty()) {
            answer = newValue
            errorMessage = ""
        } else {
            errorMessage = "Input hanya boleh berupa angka"
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = androidx.compose.ui.graphics.Brush.verticalGradient(
                    colors = listOf(Color(0xFF2C1E73), Color(0xFF512DA8))
                )
            )
            .imePadding(), // Menambahkan padding untuk menghindari penutupan dengan keyboard
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Nomor soal dengan border putih
        Text(
            text = "Q${currentIndex + 1}/$totalQuestions",
            fontSize = 18.sp,
            color = Color.White,
            modifier = Modifier
                .align(Alignment.End)
                .padding(end = 16.dp, bottom = 16.dp)
                .background(
                    color = Color.Transparent,
                    shape = RoundedCornerShape(50)
                )
                .border(
                    width = 2.dp,
                    color = Color.White,
                    shape = RoundedCornerShape(50)
                )
                .padding(horizontal = 16.dp, vertical = 8.dp)
        )

        // Gambar soal
        currentQuestion?.let { question ->
            val bitmap = BitmapFactory.decodeByteArray(question.image, 0, question.image.size)
            Image(
                bitmap = bitmap.asImageBitmap(),
                contentDescription = "Question Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(300.dp)
                    .padding(16.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Input jawaban dan tombol terpisah
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 48.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // TextField untuk jawaban
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(50.dp)
                    .background(Color.White, shape = RoundedCornerShape(8.dp))
                    .padding(horizontal = 8.dp, vertical = 12.dp)
            ) {
                BasicTextField(
                    value = answer,
                    onValueChange = { onAnswerChanged(it) },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number
                    ), // Menambahkan keyboard number field
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp),
                    singleLine = true
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Tombol berupa ikon
            Button(
                onClick = {
                    if (answer.text.isEmpty()) {
                        errorMessage = "Silakan masukkan jawaban terlebih dahulu."
                    } else if (isValidNumber(answer.text)) {
                        coroutineScope.launch {
                            if (currentQuestion?.answer?.toIntOrNull() == answer.text.toIntOrNull()) {
                                correctAnswers++
                            }

                            if (currentIndex < totalQuestions - 1) {
                                currentIndex++
                                answer = TextFieldValue("")
                                currentQuestion = repository.getQuestionById(questionIds[currentIndex])
                            } else {
                                navController.navigate("hasilTestScreen/$totalQuestions/$correctAnswers")
                            }
                        }
                    } else {
                        errorMessage = "Input hanya boleh berupa angka."
                    }
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFA4A4A6)),
                enabled = answer.text.isNotEmpty() && isValidNumber(answer.text),
                modifier = Modifier
                    .size(50.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.button),
                    contentDescription = "Next",
                    tint = Color.Black,
                    modifier = Modifier.size(34.dp)
                )
            }
        }

        // Pesan error
        if (errorMessage.isNotEmpty()) {
            Text(
                text = errorMessage,
                color = Color.Red,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}