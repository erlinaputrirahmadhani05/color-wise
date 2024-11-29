package com.cipta.colorwise.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.draw.clip
import com.cipta.colorwise.R

@Composable
fun DoctorScreen(onBackClicked: () -> Unit) {
    val RedOrange = Color(0xFFF94F52) // Tambahkan warna RedOrange
    var selectedDoctor by remember { mutableStateOf<Doctor?>(null) } // State untuk dokter yang dipilih

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF2C1E73)) // Hilangkan gradasi background untuk konsistensi
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            // Header Image
            Image(
                painter = painterResource(id = R.drawable.dokter), // Pastikan dokter.png ada di drawable
                contentDescription = "Header Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .padding(8.dp)
                    .clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Title
            Text(
                text = "Daftar Dokter",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(vertical = 4.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Doctor List
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                DoctorItem(
                    imageRes = R.drawable.dokter1,
                    name = "Dr. Maya Andini, Sp.M",
                    specialization = "Oftalmologis",
                    schedule = "Jadwal Praktek: Senin - Jumat",
                    onDetailClick = {
                        selectedDoctor = Doctor(
                            name = "Dr. Maya Andini, Sp.M",
                            specialization = "Oftalmologis",
                            description = "Dokter spesialis mata yang berpengalaman lebih dari 10 tahun dalam menangani berbagai kasus kesehatan mata. Mencakup pemeriksaan buta warna, koreksi penglihatan, dan terapi gangguan penglihatan anak-anak.",
                            schedule = "Senin - Jumat",
                            practiceHours = "08:00 - 20:00",
                            contact = "081234567890",
                            imageRes = R.drawable.dokter1
                        )
                    }
                )

                DoctorItem(
                    imageRes = R.drawable.dokter2,
                    name = "Dr. Rina Cantika, Sp.M",
                    specialization = "Oftalmologis",
                    schedule = "Jadwal Praktek: Senin - Jumat",
                    onDetailClick = {
                        selectedDoctor = Doctor(
                            name = "Dr. Rina Cantika, Sp.M",
                            specialization = "Oftalmologis",
                            description = "Dokter spesialis mata yang berpengalaman lebih dari 10 tahun dalam menangani berbagai kasus kesehatan mata. Mencakup pemeriksaan buta warna, koreksi penglihatan, dan terapi gangguan penglihatan anak-anak.",
                            schedule = "Senin - Jumat",
                            practiceHours = "08:00 - 20:00",
                            contact = "081234567891",
                            imageRes = R.drawable.dokter2
                        )
                    }
                )

                DoctorItem(
                    imageRes = R.drawable.dokter3,
                    name = "Dr. Budi Prakoso, Sp.M",
                    specialization = "Oftalmologis",
                    schedule = "Jadwal Praktek: Senin - Jumat",
                    onDetailClick = {
                        selectedDoctor = Doctor(
                            name = "Dr. Budi Prakoso, Sp.M",
                            specialization = "Oftalmologis",
                            description = "Dokter spesialis mata yang berpengalaman lebih dari 10 tahun dalam menangani berbagai kasus kesehatan mata. Mencakup pemeriksaan buta warna, koreksi penglihatan, dan terapi gangguan penglihatan anak-anak.",
                            schedule = "Senin - Jumat",
                            practiceHours = "08:00 - 20:00",
                            contact = "081234567892",
                            imageRes = R.drawable.dokter3
                        )
                    }
                )
            }

            Spacer(modifier = Modifier.weight(1f))
        }

        // Back Button at the Bottom Center
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.BottomCenter // Menempatkan tombol di bagian bawah
        ) {
            Button(
                onClick = onBackClicked,
                colors = ButtonDefaults.buttonColors(containerColor = RedOrange), // Gunakan warna solid RedOrange
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 16.dp)
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp) // Radius sudut
            ) {
                Text(
                    text = "Back",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White // Teks berwarna putih
                )
            }
        }

        // Doctor Detail Dialog
        selectedDoctor?.let { doctor ->
            DoctorDetailDialog(doctor = doctor, onDismiss = { selectedDoctor = null })
        }
    }
}

@Composable
fun DoctorItem(imageRes: Int, name: String, specialization: String, schedule: String, onDetailClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, shape = RoundedCornerShape(12.dp))
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Doctor Image
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = "Doctor Image",
            modifier = Modifier
                .size(56.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Color.Gray, shape = RoundedCornerShape(12.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(12.dp))

        // Doctor Info Column
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = name, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.Black)
            Text(text = specialization, fontSize = 12.sp, color = Color.Gray)
            Text(text = schedule, fontSize = 12.sp, color = Color.Gray)

            Spacer(modifier = Modifier.height(4.dp))

            // Detail Button
            Button(
                onClick = onDetailClick,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF280F71)),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(32.dp)
            ) {
                Text(text = "Detail", fontSize = 12.sp, color = Color.White)
            }
        }
    }
}

@Composable
fun DoctorDetailDialog(doctor: Doctor, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Box(modifier = Modifier.fillMaxWidth()) {
                // Tombol "<" di pojok kiri atas dengan warna 6400FF
                TextButton(
                    onClick = onDismiss,
                    modifier = Modifier.align(Alignment.CenterStart)
                ) {
                    Text(text = "<", fontSize = 18.sp, color = Color(0xFF6400FF))
                }

                // Gambar dan nama dokter di tengah
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.align(Alignment.Center)
                ) {
                    Image(
                        painter = painterResource(id = doctor.imageRes),
                        contentDescription = "Doctor Image",
                        modifier = Modifier
                            .size(40.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color.Gray)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = doctor.name, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                }
            }
        },
        text = {
            Column {
                Text(text = doctor.specialization, fontWeight = FontWeight.Bold, color = Color.Gray)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = doctor.description)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Jadwal Praktek: ${doctor.schedule}")
                Text(text = "Jam Praktek: ${doctor.practiceHours}")
                Text(text = "Kontak: ${doctor.contact}")
            }
        },
        confirmButton = {}
    )
}


data class Doctor(
    val name: String,
    val specialization: String,
    val description: String,
    val schedule: String,
    val practiceHours: String,
    val contact: String,
    val imageRes: Int
)