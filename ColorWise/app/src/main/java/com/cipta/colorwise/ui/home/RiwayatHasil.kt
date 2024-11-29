package com.cipta.colorwise.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.cipta.colorwise.R

@Composable
fun RiwayatHasilScreen(
    totalQuestions: Int,
    correctAnswers: Int,
    navController: NavController // Menambahkan navController
) {
    val incorrectAnswers = totalQuestions - correctAnswers // Hitung jawaban salah

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFF2C1E73), Color(0xFF512DA8)) // Background gradasi
                )
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        // Profile Icon and Name
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(Color(0xFF87CEFA), CircleShape) // Light blue background
            ) {
                // Placeholder for profile icon
                Box(
                    modifier = Modifier.align(Alignment.Center)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.logocolorwise),
                        contentDescription = "logocolorwise",
                        modifier = Modifier.size(64.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "NAMA", // Tampilkan nama pengguna atau ganti sesuai keperluan
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }

        // Score Details
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            ScoreItem(label = "TOTAL PERCOBAAN", value = totalQuestions.toString())
            ScoreItem(label = "BENAR", value = correctAnswers.toString(), isCorrect = true)
            ScoreItem(label = "SALAH", value = incorrectAnswers.toString(), isCorrect = false)
        }

        Spacer(modifier = Modifier.weight(1f))

        // Tombol Back
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.BottomCenter
        ) {
            Button(
                onClick = {
                    // Navigasi kembali
                    navController.popBackStack()
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
                    .height(56.dp), // Tinggi tombol
                shape = RoundedCornerShape(12.dp), // Radius sudut
                elevation = ButtonDefaults.buttonElevation(0.dp) // Hilangkan bayangan
            ) {
                // Latar belakang gradien untuk tombol
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(Color(0xFF7A4BFF), Color(0xFFB388FF)) // Gradasi warna tombol
                            ),
                            shape = RoundedCornerShape(12.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Kembali",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White // Teks berwarna putih
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}

// Komponen tambahan untuk menampilkan score item
@Composable
fun ScoreItem(
    label: String,
    value: String,
    isCorrect: Boolean = true
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = value,
            color = if (isCorrect) Color(0xFF4CAF50) else Color(0xFFF44336),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
    }
}