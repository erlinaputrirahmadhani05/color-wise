package com.cipta.colorwise.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.cipta.colorwise.R
import com.cipta.colorwise.viewmodel.ColorWiseViewModel
import com.cipta.colorwise.data.HasilTes

@Composable
fun HasilTestScreen(
    totalQuestions: Int,
    correctAnswers: Int,
    onBackClicked: () -> Unit,
    onDokterClicked: () -> Unit,
    navController: NavController,
    viewModel: ColorWiseViewModel
) {
    val incorrectAnswers = totalQuestions - correctAnswers

    // Warna tombol
    val RedOrange = Color(0xFFF94F52)
    val LightPink = Color(0xFFF472B6)
    val SkyBlue = Color(0xFF15C8DB)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFF2C1E73), Color(0xFF512DA8)) // Background gradasi
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Spacer(modifier = Modifier.height(8.dp))

            // Gambar dan deskripsi dalam satu Box
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .background(Color(0xFF3D2C8D), shape = RoundedCornerShape(16.dp))
                    .padding(16.dp)
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    // Gambar
                    Image(
                        painter = painterResource(id = R.drawable.mata), // Nama gambar harus benar
                        contentDescription = "Eye Test Image",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp),
                        contentScale = ContentScale.Crop
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Deskripsi hasil tes
                    Text(
                        text = "Hasil Tes Anda:",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = """
                            Tes buta warna telah selesai! Cek hasil kamu:
                            - Jika nilai kamu di atas 7, kamu berada di zona aman. Tetap jaga kesehatan mata!
                            - Jika nilai di bawah 7, disarankan untuk konsultasi lebih lanjut. Hubungi Dokter untuk langkah berikutnya.
                        """.trimIndent(),
                        fontSize = 14.sp,
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Statistik
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .background(Color.White, shape = RoundedCornerShape(16.dp))
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                StatistikItem(label = "TOTAL PERCOBAAN", value = totalQuestions.toString())
                StatistikItem(label = "BENAR", value = correctAnswers.toString())
                StatistikItem(label = "SALAH", value = incorrectAnswers.toString())
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Tombol
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Tombol Back dengan warna RedOrange
                Button(
                    onClick = onBackClicked,
                    colors = ButtonDefaults.buttonColors(containerColor = RedOrange),
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier
                        .width(120.dp)  // Ukuran lebih kecil
                        .height(48.dp)  // Ukuran lebih kecil
                ) {
                    Text(
                        text = "Back",
                        fontSize = 16.sp,  // Ukuran font sedikit lebih kecil
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }

                // Tombol Dokter dengan warna LightPink
                Button(
                    onClick = onDokterClicked,
                    colors = ButtonDefaults.buttonColors(containerColor = LightPink),
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier
                        .width(120.dp)  // Ukuran lebih kecil
                        .height(48.dp)  // Ukuran lebih kecil
                ) {
                    Text(
                        text = "Dokter",
                        fontSize = 16.sp,  // Ukuran font sedikit lebih kecil
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))


            // Simpan hasil tes ke database
            LaunchedEffect(Unit) {
                viewModel.addHasilTes(
                    HasilTes(
                        totalPercobaan = totalQuestions,
                        benar = correctAnswers,
                        salah = incorrectAnswers,
                        timestamp = System.currentTimeMillis() // Gunakan waktu saat ini
                    )
                )
            }
        }
    }
}

@Composable
fun StatistikItem(label: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = label,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = value,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
    }
}

@Composable
fun GradientButton(
    text: String,
    colors: List<Color>,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .width(160.dp)
            .height(60.dp),
        elevation = ButtonDefaults.buttonElevation(0.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(colors = colors),
                    shape = RoundedCornerShape(16.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}
