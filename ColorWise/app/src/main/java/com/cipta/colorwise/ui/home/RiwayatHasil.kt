package com.cipta.colorwise.ui.home

import androidx.compose.foundation.Image
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.cipta.colorwise.R

val RedOrange = Color(0xFFF94F52)
val PurpleColor = Color(0xFF1D0D62)

@Composable
fun RiwayatHasilScreen(
    totalQuestions: Int,
    correctAnswers: Int,
    navController: NavController
) {
    val incorrectAnswers = totalQuestions - correctAnswers


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(PurpleColor),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(120.dp)
            ) {
                // Pastikan gambar yang digunakan memiliki transparansi
                Image(
                    painter = painterResource(id = R.drawable.user), // Ganti dengan gambar transparan
                    contentDescription = "logo background",
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(CircleShape) // Membuat gambar berbentuk lingkaran
                )
                Box(modifier = Modifier.align(Alignment.Center)) {
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Nama Pengguna", // Ganti dengan nama pengguna yang sebenarnya
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
//
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.White,       // Warna putih penuh di atas
                            Color.White.copy(alpha = 0.7f), // Putih dengan sedikit transparansi
                            Color.Transparent  // Transparansi penuh di bawah
                        ),
                        startY = 0f,          // Awal gradasi (di bagian atas)
                        endY = 500f           // Akhir gradasi (lebih jauh ke bawah)
                    ),
                    shape = RoundedCornerShape(24.dp) // Bentuk sudut melengkung
                )
                .padding(vertical = 24.dp, horizontal = 16.dp) // Memberikan ruang dalam
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(18.dp)
            ) {
                ScoreItemWithImage(
                    label = "TOTAL PERCOBAAN",
                    value = totalQuestions.toString(),
                    iconRes = R.drawable.all
                )
                ScoreItemWithImage(
                    label = "BENAR",
                    value = correctAnswers.toString(),
                    isCorrect = true,
                    iconRes = R.drawable.benar
                )
                ScoreItemWithImage(
                    label = "SALAH",
                    value = incorrectAnswers.toString(),
                    isCorrect = false,
                    iconRes = R.drawable.salah
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.BottomCenter
        ) {
            Button(
                onClick = {
                    navController.popBackStack()
                },
                colors = ButtonDefaults.buttonColors(containerColor = RedOrange),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
                    .height(60.dp),
                shape = RoundedCornerShape(16.dp),
                elevation = ButtonDefaults.buttonElevation(4.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            color = RedOrange,
                            shape = RoundedCornerShape(16.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Back",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}
@Composable
fun ScoreItemWithImage(
    label: String,
    value: String,
    isCorrect: Boolean = true,
    iconRes: Int
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(vertical = 16.dp)
            .background(Color.White, RoundedCornerShape(16.dp)), // White background for the list item
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = iconRes),
                contentDescription = "$label Icon",
                modifier = Modifier.size(36.dp)
            )
            Spacer(modifier = Modifier.width(18.dp))
            Text(
                text = label,
                color = Color.Black,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Text(
            text = value,
            color = if (isCorrect) Color(0xFF000000) else Color(0xFF000000),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
