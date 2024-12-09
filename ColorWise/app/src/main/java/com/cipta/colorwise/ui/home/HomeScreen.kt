package com.cipta.colorwise.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
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
import com.cipta.colorwise.viewmodel.ColorWiseViewModel

// Button Colors
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
                        Color(0xFF2C1E73), // Dark purple
                        Color(0xFF512DA8)  // Light purple
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
            .height(60.dp), // Button size
        shape = RoundedCornerShape(12.dp), // Rounded corners
        colors = ButtonDefaults.buttonColors(containerColor = color),
        elevation = ButtonDefaults.buttonElevation(8.dp) // Button shadow
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 2.dp), // Padding on the left
            verticalAlignment = Alignment.CenterVertically, // Align icon and text vertically
            horizontalArrangement = Arrangement.Start // Icon and text start from the left
        ) {
            // Icon with white background
            Box(
                modifier = Modifier
                    .size(40.dp) // Icon size
                    .background(Color.White, RoundedCornerShape(8.dp)) // White background with rounded corners
                    .padding(8.dp) // Padding inside the icon box
            ) {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = text,
                    tint = Color.Black, // Icon color set to black for contrast with the white background
                    modifier = Modifier.size(24.dp) // Icon size inside the box
                )
            }

            Spacer(modifier = Modifier.width(12.dp)) // Space between icon and text

            // Button text
            Text(
                text = text,
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                ),
                modifier = Modifier.align(Alignment.CenterVertically) // Vertically center the text
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
            // Logo on the top left
            IconButton(onClick = { }) {
                Icon(
                    painter = painterResource(id = R.drawable.logocolorwise),
                    contentDescription = "Color Wise Logo",
                    tint = Color.Unspecified,
                    modifier = Modifier.size(80.dp)
                )
            }

            // Result History Button
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
                    style = TextStyle( // Use TextStyle
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
            // Hello, Name text
            Text(
                text = "Hello, ${userName ?: "Name"}!",
                style = TextStyle( // Use TextStyle
                    fontSize = 30.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.White
                )
            )

            Spacer(modifier = Modifier.height(32.dp)) // Space between text and first button

            // EYE TEST Button
            SolidButtonWithIcon(
                text = "TES MATA",
                color = OrangeBrown,
                icon = R.drawable.eye, // Replace with appropriate icon
                onClick = { navController.navigate("splashtest") }
            )

            Spacer(modifier = Modifier.height(24.dp)) // Larger space between buttons

            // DOCTOR Button
            SolidButtonWithIcon(
                text = "DOKTER",
                color = LightPink,
                icon = R.drawable.doctor, // Replace with appropriate icon
                onClick = { navController.navigate("doctor") }
            )

            Spacer(modifier = Modifier.height(24.dp)) // Larger space between buttons

            // COLOR BLIND INFORMATION Button
            SolidButtonWithIcon(
                text = "INFORMASI BUTA WARNA",
                color = MintGreen,
                icon = R.drawable.info, // Replace with appropriate icon
                onClick = { navController.navigate("info") }
            )
        }

        // ColorWise Text and Horizontal Line
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
                    widthFraction = 0.5f, // Shorter horizontal line
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }
    }
}
