package com.cipta.colorwise.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.foundation.Canvas
import androidx.compose.ui.geometry.Offset

@Composable
fun CustomButton(
    text: String,
    onClick: () -> Unit,
    fontSize: TextUnit = 16.sp
) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFDF7132)),
        modifier = Modifier
            .width(200.dp)
            .height(50.dp)
    ) {
        Text(
            text = text,
            fontSize = fontSize,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun HorizontalLine(
    color: Color,
    thickness: Dp,
    widthFraction: Float,
    modifier: Modifier = Modifier
) {
    Canvas(
        modifier = modifier
            .fillMaxWidth(widthFraction)
            .height(thickness)
    ) {
        drawLine(
            color = color,
            start = Offset(0f, size.height / 2),
            end = Offset(size.width, size.height / 2),
            strokeWidth = thickness.toPx()
        )
    }
}

@Composable
fun SplashTest(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF2C1E73),
                        Color(0xFF512DA8)
                    )
                )
            )
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Box untuk judul dan deskripsi
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
                    .background(
                        color = Color(0x33000000),
                        shape = RoundedCornerShape(12.dp)
                    )
                    .padding(16.dp)
            ) {
                Text(
                    text = "!!! Sebelum memulai tes buta warna !!!",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                Text(
                    text = "1. Lepaskan semua kacamata dengan lensa berwarna untuk memastikan hasil yang akurat.",
                    color = Color.White,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "2. Tingkatkan kecerahan layar ponsel anda ke tingkat tinggi, karena pencahayaan redup dapat memengaruhi kemampuan anda melihat warna dengan jelas.",
                    color = Color.White,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "3. Lakukan tes di ruangan dengan pencahayaan yang cukup.",
                    color = Color.White,
                    fontSize = 16.sp
                )
            }
            Spacer(modifier = Modifier.height(24.dp))

            // Tombol MULAI TES
            CustomButton(
                text = "MULAI TES",
                onClick = { navController.navigate("test") },
                fontSize = 18.sp
            )
        }

        // Teks "ColorWise" dan garis horizontal
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
