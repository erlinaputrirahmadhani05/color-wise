package com.cipta.colorwise.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.cipta.colorwise.R
import com.cipta.colorwise.viewmodel.ColorWiseViewModel

val RedOrange = Color(0xFFF94F52)
val PurpleColor = Color(0xFF1D0D62)

@Composable
fun RiwayatHasilScreen(
    navController: NavController,
    viewModel: ColorWiseViewModel,
    totalQuestions: Int,
    correctAnswers: Int
) {
    val hasilTesList by viewModel.hasilTesList.collectAsState(emptyList())
    val incorrectAnswers = totalQuestions - correctAnswers

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(PurpleColor)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(bottom = 80.dp) // Menambahkan padding bawah agar tombol Back tidak tertutup

        ) {
            if (hasilTesList.isEmpty()) {
                item {
                    Text(
                        text = "Belum ada data riwayat.",
                        color = Color.White,
                        fontSize = 18.sp
                    )
                }
            } else {
                items(hasilTesList.size) { index ->
                    val hasil = hasilTesList[index]
                    RiwayatHasilItem(
                        totalPercobaan = hasil.totalPercobaan,
                        benar = hasil.benar,
                        salah = hasil.salah,
                        timestamp = hasil.timestamp,
                        onClick = {
                            // Navigasi ke detail
                            navController.navigate(
                                "riwayat_detail/${hasil.totalPercobaan}/${hasil.benar}/${hasil.salah}/${hasil.timestamp}"
                            )
                        },
                        onDelete = {
                            // Hapus data dari ViewModel
                            viewModel.deleteHasil(hasil.id)
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Tombol Kembali
        Button(
            onClick = { navController.popBackStack() },
            colors = ButtonDefaults.buttonColors(containerColor = RedOrange),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(bottom = 16.dp)
                .height(50.dp)
                .align(Alignment.BottomCenter), // Tombol ditempatkan di bagian bawah layar
            shape = RoundedCornerShape(16.dp)
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


@Composable
fun RiwayatHasilItem(
    totalPercobaan: Int,
    benar: Int,
    salah: Int,
    timestamp: Long,
    onClick: () -> Unit,
    onDelete: () -> Unit
) {
    val formattedDate = android.text.format.DateFormat.format("dd MMM yyyy, HH:mm", timestamp).toString()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .background(Color.White, RoundedCornerShape(16.dp))
            .clickable(onClick = onClick)
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(
                text = "Tanggal: $formattedDate",
                color = Color.Black,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = "Percobaan: $totalPercobaan",
                color = Color.Black,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Column(horizontalAlignment = Alignment.End) {
            Text(
                text = "Benar: $benar",
                color = Color(0xFF34D399),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Salah: $salah",
                color = Color(0xFFF87171),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
            // Tombol Hapus
            IconButton(onClick = { onDelete() }) { // Panggil fungsi onDelete saat tombol ditekan
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Hapus",
                    tint = Color.Red)

            }
        }
    }
}
