package com.cipta.colorwise.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.cipta.colorwise.R

// Warna tombol
val OrangeBrown = Color(0xFFDF7132)
val LightPink = Color(0xFFF472B6)
val MintGreen = Color(0xFF34D399)
val SkyBlue = Color(0xFF15C8DB)

@Composable
fun CustomBackground() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF2C1E73), // Warna ungu gelap
                        Color(0xFF512DA8)  // Warna ungu terang
                    )
                )
            )
    )
}

@Composable
fun SolidButtonWithIcon(
    text: String,
    color: Color,
    icon: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(60.dp), // Ukuran tombol
        shape = RoundedCornerShape(12.dp), // Sudut membulat
        colors = ButtonDefaults.buttonColors(containerColor = color),
        elevation = ButtonDefaults.buttonElevation(8.dp) // Bayangan tombol
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 2.dp), // Padding di kiri
            verticalAlignment = Alignment.CenterVertically, // Pusatkan ikon dan teks secara vertikal
            horizontalArrangement = Arrangement.Start // Ikon dan teks mulai dari kiri
        ) {
            // Ikon dengan background putih
            Box(
                modifier = Modifier
                    .size(40.dp) // Ukuran ikon
                    .background(Color.White, RoundedCornerShape(8.dp)) // Background putih dengan sudut membulat
                    .padding(8.dp) // Padding di dalam ikon
            ) {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = text,
                    tint = Color.Black, // Warna ikon diubah jadi hitam agar kontras dengan background putih
                    modifier = Modifier.size(24.dp) // Ukuran ikon di dalam box
                )
            }

            Spacer(modifier = Modifier.width(12.dp)) // Jarak antara ikon dan teks

            // Teks tombol
            Text(
                text = text,
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                ),
                modifier = Modifier.align(Alignment.CenterVertically) // Posisikan teks di tengah vertikal
            )
        }
    }
}


@Composable
fun HomeScreen(navController: NavController, viewModel: ColorWiseViewModel) {
    val userNameState = viewModel.userName.collectAsState()
    val userName = userNameState.value

    Box(modifier = Modifier.fillMaxSize()) {
        CustomBackground()

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Logo di kiri atas
            IconButton(onClick = { }) {
                Icon(
                    painter = painterResource(id = R.drawable.logocolorwise),
                    contentDescription = "Logo Color Wise",
                    tint = Color.Unspecified,
                    modifier = Modifier.size(80.dp)
                )
            }

            // Tombol Riwayat Hasil
            Button(
                onClick = {
                    val totalQuestions = 10
                    val correctAnswers = 8
                    navController.navigate("riwayathasil/$totalQuestions/$correctAnswers")
                },
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = SkyBlue),
                modifier = Modifier.height(40.dp)
            ) {
                Text(
                    text = "Riwayat Hasil",
                    style = TextStyle( // Menggunakan TextStyle
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Teks Halo, Nama
            Text(
                text = "Halo, ${userName ?: "Nama"}!",
                style = TextStyle( // Gunakan TextStyle
                    fontSize = 30.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.White
                )
            )

            Spacer(modifier = Modifier.height(32.dp)) // Jarak antara teks dan tombol pertama

            // TES MATA Button
            SolidButtonWithIcon(
                text = "TES MATA",
                color = OrangeBrown,
                icon = R.drawable.eye, // Ganti dengan ikon yang sesuai
                onClick = { navController.navigate("splashtest") }
            )

            Spacer(modifier = Modifier.height(24.dp)) // Jarak lebih besar antar tombol

            // DOKTER Button
            SolidButtonWithIcon(
                text = "DOKTER",
                color = LightPink,
                icon = R.drawable.doctor, // Ganti dengan ikon yang sesuai
                onClick = { navController.navigate("doctor") }
            )

            Spacer(modifier = Modifier.height(24.dp)) // Jarak lebih besar antar tombol

            // INFORMASI BUTA WARNA Button
            SolidButtonWithIcon(
                text = "INFORMASI BUTA WARNA",
                color = MintGreen,
                icon = R.drawable.info, // Ganti dengan ikon yang sesuai
                onClick = { navController.navigate("info") }
            )
        }

        // Teks ColorWise dan Garis Horizontal
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
                .align(Alignment.BottomCenter),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "ColorWise",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                HorizontalLine(
                    color = Color.White,
                    thickness = 2.dp,
                    widthFraction = 0.5f, // Garis horizontal lebih pendek
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }
    }
}