package com.cipta.colorwise.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
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
import com.cipta.colorwise.R
import kotlinx.coroutines.delay

import kotlin.time.Duration.Companion.seconds

@Composable
fun SplashScreen(onSplashFinished: () -> Unit) {
    // Menunggu beberapa waktu sebelum navigasi ke layar utama
    LaunchedEffect(Unit) {
        delay(2000)
        onSplashFinished()
    }
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
            verticalArrangement = Arrangement.Center
        ) {
            // Hanya Logo tanpa latar belakang lingkaran
            val logoPainter = painterResource(id = R.drawable.logocolorwise) // Ambil resource logo
            Image(
                painter = logoPainter,
                contentDescription = "Logo",
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(250.dp) // Atur ukuran logo sesuai kebutuhan
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Subtitle Text
            Text(
                text = "Tes Buta Warna",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Center
            )
        }
    }
}