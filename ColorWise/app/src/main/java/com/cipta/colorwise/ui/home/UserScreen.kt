package com.cipta.colorwise.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cipta.colorwise.ui.theme.ColorWiseTheme

@Composable
fun SolidButton(
    text: String,
    color: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .width(100.dp) // Lebar tombol
            .height(50.dp), // Tinggi tombol
        shape = RoundedCornerShape(8.dp), // Sudut membulat kecil
        colors = ButtonDefaults.buttonColors(containerColor = color), // Warna solid
        elevation = ButtonDefaults.buttonElevation(0.dp) // Menghilangkan bayangan
    ) {
        Text(
            text = text,
            fontSize = 16.sp, // Ukuran font lebih kecil
            fontWeight = FontWeight.Bold,
            color = Color.White // Teks berwarna putih
        )
    }
}

@Composable
fun InputScreen(onNextClicked: () -> Unit, viewModel: ColorWiseViewModel = viewModel()) {
    val name = remember { mutableStateOf(TextFieldValue()) }
    var errorMessage by remember { mutableStateOf("") }
    var isNameEmpty by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFF2C1E73), Color(0xFF512DA8))
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Siapa namamu?",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = name.value,
                onValueChange = {
                    if (it.text.all { char -> char.isLetter() || char.isWhitespace() }) {
                        name.value = it
                        errorMessage = ""
                        isNameEmpty = it.text.isEmpty()
                    } else {
                        errorMessage = "Nama hanya boleh mengandung huruf."
                    }
                },
                placeholder = { Text("Masukkan nama") },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White, shape = RoundedCornerShape(8.dp))
                    .padding(horizontal = 16.dp),
                singleLine = true
            )

            if (errorMessage.isNotEmpty() || isNameEmpty) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = errorMessage.ifEmpty { "Nama harus diisi." },
                    fontSize = 12.sp,
                    color = Color.Red
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            SolidButton(
                text = "Next",
                color = Color(0xFFDF7132),
                onClick = {
                    if (name.value.text.isEmpty()) {
                        isNameEmpty = true
                        errorMessage = "Nama harus diisi."
                    } else {
                        // Simpan nama pengguna ke ViewModel dan SharedPreferences
                        viewModel.setUserName(name.value.text)
                        onNextClicked()
                    }
                },
                modifier = Modifier
                    .padding(top = 16.dp)
            )

        }
    }
}