package com.cipta.colorwise.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.cipta.colorwise.R // Pastikan ini sesuai dengan resource Anda
import com.cipta.colorwise.data.User
import com.cipta.colorwise.ui.theme.PurpleColor
import com.cipta.colorwise.ui.theme.RedOrange
import com.cipta.colorwise.viewmodel.ColorWiseViewModel

@Composable
fun RiwayatDetailScreen(
    totalPercobaan: Int,
    benar: Int,
    salah: Int,
    timestamp: Long,
    navController: NavController,
    viewModel: ColorWiseViewModel
) {
    val formattedDate = android.text.format.DateFormat.format("dd MMM yyyy, HH:mm", timestamp).toString()
    var lastUser by remember { mutableStateOf<User?>(null) } // State untuk menyimpan user terakhir

    // Ambil user terakhir dari database
    LaunchedEffect(Unit) {
        val user = viewModel.getLastUser()
        lastUser = user
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(PurpleColor),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        // Header: Menampilkan user terakhir yang diinput
        Image(
            painter = painterResource(id = R.drawable.user),
            contentDescription = "User Avatar",
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = lastUser?.userName ?: "Nama belum tersedia", // Nama user terakhir
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Detail Data
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            DetailItem(label = "TOTAL PERCOBAAN", value = totalPercobaan.toString(), icon = R.drawable.all)
            DetailItem(label = "BENAR", value = benar.toString(), icon = R.drawable.benar)
            DetailItem(label = "SALAH", value = salah.toString(), icon = R.drawable.salah)
            DetailItem(label = "WAKTU", value = formattedDate, icon = R.drawable.clock)
        }

        Spacer(modifier = Modifier.weight(1f))

        // Tombol Kembali
        Button(
            onClick = { navController.popBackStack() },
            colors = ButtonDefaults.buttonColors(backgroundColor = RedOrange),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .height(50.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(text = "Back", fontSize = 18.sp, color = Color.White)
        }
    }
}

@Composable
fun DetailItem(label: String, value: String, icon: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .background(Color.White, RoundedCornerShape(12.dp))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = label,
            tint = PurpleColor,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = label,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = PurpleColor,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = value,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = Color.Black
        )
    }
}

