package com.cipta.colorwise.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.cipta.colorwise.ui.navigation.NavGraph
import com.cipta.colorwise.ui.theme.ColorWiseTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Menginisialisasi database melalui DatabaseInitializer
        val databaseInitializer = DatabaseInitializer(applicationContext)
        databaseInitializer.initializeDatabase()

        setContent {
            ColorWiseApp()
        }
    }
}

@Composable
fun ColorWiseApp() {
    ColorWiseTheme {
        val navController = rememberNavController()
        val systemUiController = rememberSystemUiController()

        val gradientTopColor = Color(0xFF2C1E73)
        val gradientBottomColor = Color(0xFF512DA8)

        SideEffect {
            systemUiController.setStatusBarColor(
                color = gradientTopColor,
                darkIcons = false
            )
        }

        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(gradientTopColor, gradientBottomColor)
                    )
                ),
            content = { paddingValues ->
                NavGraph(
                    navController = navController,
                    modifier = Modifier.padding(paddingValues)
                )
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ColorWiseApp()
}