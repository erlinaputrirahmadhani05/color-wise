package com.cipta.colorwise.ui.home

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Icon
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.remember

@Composable
fun ColorBlindInfoScreen(
    onBackClicked: () -> Unit,
    onInfoClicked: (String) -> Unit
) {
    val dialogState = remember { mutableStateOf<String?>(null) }

    // Fungsi untuk toggle visibilitas dialog berdasarkan kategori
    fun toggleDialog(category: String) {
        if (dialogState.value == category) {
            dialogState.value = null // Menutup dialog jika sudah terbuka
        } else {
            dialogState.value = category // Menampilkan dialog untuk kategori yang dipilih
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFF2C1E73), Color(0xFF512DA8))
                )
            )
            .padding(16.dp)
            .verticalScroll(rememberScrollState()) // Menambahkan scroll hanya pada halaman utama
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(Color(0xFFBEB6F7), Color(0xFFBEB6F7))
                    ),
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(vertical = 8.dp, horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = "Informasi Buta Warna",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }

        Spacer(modifier = Modifier.height(50.dp))

        // List tombol untuk kategori informasi buta warna
        listOf(
            "Definisi Buta Warna" to "definisi",
            "Jenis-Jenis Buta Warna" to "jenis",
            "Gejala Buta Warna" to "gejala",
            "Penyebab Buta Warna" to "penyebab",
            "Pencegahan Buta Warna" to "pencegahan",
            "Penanganan Buta Warna" to "penanganan"
        ).forEach { (text, category) ->
            // Tombol untuk masing-masing kategori
            InfoButton(
                text = text,
                buttonColor = Color(0xFFFFFFFF)
            ) {
                toggleDialog(category)
            }

            // Dialog tepat di bawah masing-masing tombol
            AnimatedVisibility(
                visible = dialogState.value == category,
                enter = slideInVertically(
                    initialOffsetY = { it / 4 },
                    animationSpec = tween(durationMillis = 500)
                ),
                exit = slideOutVertically(
                    targetOffsetY = { -it },
                    animationSpec = tween(durationMillis = 300)
                )
            ) {
                // Box atau Column biasa tanpa scroll di sini untuk konten dialog
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .padding(top = 16.dp)
                        .background(Color.White, shape = RoundedCornerShape(16.dp))
                ) {
                    Column(
                        modifier = Modifier
                            .padding(24.dp)
                    ) {
                        when (category) {
                            "definisi" -> {
                                Text(
                                    text = """
                            Buta warna (colour blind) adalah suatu kelainan atau gangguan pada mata yang memiliki kelemahan penglihatan warna disebabkan ketidakmampuan sel-sel kerucut mata untuk menangkap suatu spektrum warna tertentu sehingga warna yang dilihat tidak terlihat sesuai dengan warna yang dilihat mata normal. Buta warna merupakan penyakit kelainan mata yang ditentukan oleh gen resesif pada kromosom seks, khususnya terpaut pada kromosom X atau kondisi ketika sel-sel retina tidak mampu merespon warna dengan semestinya.
                        """.trimIndent(),
                                    fontSize = 18.sp
                                )
                            }
                            "jenis" -> {
                                Text(
                                    text = """
                            1. Buta Warna Merah-Hijau
                            Buta warna merah-hijau memiliki ciri-ciri sebagai berikut:
                            - Warna kuning dan hijau terlihat memerah.
                            - Warna oranye, merah, dan kuning terlihat seperti hijau.
                            - Warna merah terlihat seperti hitam, atau warna merah bisa juga terlihat kuning kecokelatan dan warna hijau, seperti warna krem.

                            2. Buta Warna Biru-Kuning
                            Sedangkan buta warna biru-kuning ditandai dengan ciri-ciri berikut:
                            - Warna biru terlihat kehijauan serta sulit membedakan warna merah muda dengan kuning dan merah.
                            - Warna biru terlihat seperti hijau, dan warna kuning terlihat seperti abu-abu atau ungu terang.

                            3. Buta Warna Total
                            Berbeda dari kedua tipe buta warna di atas, orang yang mengalami total warna buta membedakan semua warna. Bahkan, beberapa pengidapnya hanya bisa melihat warna putih, abu-abu, dan hitam.
                        """.trimIndent(),
                                    fontSize = 18.sp
                                )
                            }
                            "gejala" -> {
                                Text(
                                    text = """
                            Gejala buta warna yang umumnya terjadi adalah:
                            1. Sulit membedakan warna dan kecerahan warna.
                            2. Sulit membedakan bayangan warna yang mirip, seperti merah dengan hijau atau biru dengan kuning.

                            Orang yang tidak dapat membedakan warna sama sekali, atau semua warna terlihat abu-abu, disebut dengan akromatopsia. Kondisi ini sangat jarang, dan biasanya berhubungan dengan ambliopia (mata malas), nistagmus (gerakan 'mata cepat dan tidak disadari), sensitif terhadap cahaya, dan buruknya ketajaman penglihatan.
                        """.trimIndent(),
                                    fontSize = 18.sp
                                )
                            }
                            "penyebab" -> {
                                Text(
                                    text = """
                            Penyebab utama buta warna adalah:
                            - Buta warna umumnya diwariskan secara genetik. Ini biasanya terkait dengan kromosom X, sehingga lebih umum terjadi pada pria dibandingkan wanita.
                            - Beberapa kondisi medis dapat menyebabkan buta warna, termasuk:
                            - Diabetes: Dapat merusak pembuluh darah di retina, memengaruhi penglihatan warna.
                            - Glaukoma: Penyakit mata yang dapat memengaruhi kemampuan melihat warna.
                            - Katarak: Penyakit ini dapat memengaruhi cara cahaya masuk ke mata dan mengubah persepsi warna.
                            - Usia: Seiring bertambahnya usia, kemampuan penglihatan dapat menurun, termasuk persepsi warna.
                        """.trimIndent(),
                                    fontSize = 18.sp
                                )
                            }
                            "pencegahan" -> {
                                Text(
                                    text = """
                            Penyebab buta warna bukan hanya berasal dari faktor keturunan, tapi juga bisa disebabkan karena faktor luar. Berikut ini adalah beberapa tindakan pencegahan yang bisa anda lakukan:
                            1. Mengkonsumsi buah pepaya: Selain melancarkan gangguan pencernaan, pepaya mengandung vitamin A yang tinggi, membantu mencegah buta warna.
                            2. Banyak mengkonsumsi susu sapi: Susu sapi dengan kalsium tinggi sangat baik untuk pengobatan dan pencegahan buta warna.
                            3. Mengkonsumsi susu kambing: Susu kambing bisa membantu mengobati gangguan penglihatan, termasuk buta warna.
                        """.trimIndent(),
                                    fontSize = 18.sp
                                )
                            }
                            "penanganan" -> {
                                Text(
                                    text = """
                            Penanganan untuk mengobati buta warna:
                            1. Memakai lensa kontak berwarna untuk membantu membedakan warna, meskipun tidak mengembalikan penglihatan warna normal.
                            2. Memakai kacamata anti-ultraviolet untuk membantu melihat perbedaan warna lebih baik dalam cahaya redup.
                            3. Belajar untuk mengandalkan isyarat terang-gelap atau peletakkan barang bukan berdasarkan warna.
                        """.trimIndent(),
                                    fontSize = 18.sp
                                )
                            }
                            else -> {
                                Text(
                                    text = "Informasi kategori $category akan ditampilkan di sini.",
                                    fontSize = 18.sp
                                )
                            }
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        // Tombol Back dengan ukuran lebih kecil
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.BottomCenter
        ) {
            Button(
                onClick = onBackClicked,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF94F52)), // Warna solid RedOrange
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 25.dp)  // Padding horizontal untuk lebar tombol
                    .height(48.dp), // Mengubah tinggi tombol menjadi lebih kecil
                shape = RoundedCornerShape(12.dp), // Radius sudut
                elevation = ButtonDefaults.buttonElevation(0.dp) // Hilangkan bayangan
            ) {
                Text(
                    text = "Back",
                    fontSize = 18.sp, // Ukuran font yang sedikit lebih kecil
                    fontWeight = FontWeight.Bold,
                    color = Color.White // Teks berwarna putih
                )
            }
        }
    }
}
        @Composable
        fun InfoButton(
            text: String,
            buttonColor: Color,
            onClick: () -> Unit
        ) {
            Button(
                onClick = onClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = buttonColor),
                elevation = ButtonDefaults.buttonElevation(20.dp),
                contentPadding = PaddingValues(horizontal = 16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = text,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Icon(
                        imageVector = Icons.Filled.ArrowForward,
                        contentDescription = "Next",
                        tint = Color.Black
                    )
                }
            }
        }