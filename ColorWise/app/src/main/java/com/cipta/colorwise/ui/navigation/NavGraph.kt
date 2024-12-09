package com.cipta.colorwise.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.cipta.colorwise.ui.home.*
import com.cipta.colorwise.ui.test.TestScreen
import com.cipta.colorwise.viewmodel.ColorWiseViewModel

@Composable
fun NavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    viewModel: ColorWiseViewModel = viewModel() // Shared ViewModel untuk seluruh navigasi
) {
    val context = LocalContext.current

    NavHost(
        navController = navController,
        startDestination = "splash",
        modifier = modifier
    ) {
        // Splash Screen
        composable("splash") {
            SplashScreen(
                onSplashFinished = {
                    navController.navigate("input") {
                        popUpTo("splash") { inclusive = true }
                    }
                }
            )
        }

        // Input Screen
        composable("input") {
            InputScreen(
                onNextClicked = {
                    navController.navigate("home") {
                        popUpTo("input") { inclusive = true }
                    }
                },
                viewModel = viewModel
            )
        }

        // Home Screen
        composable("home") {
            HomeScreen(
                navController = navController,
                viewModel = viewModel
            )
        }

        // Splash Test Screen
        composable("splashtest") {
            SplashTest(navController = navController)
        }

        // Test Screen
        composable("test") {
            val correctAnswer = "jawaban benar" // Sesuaikan jika dinamis
            val totalQuestions = 10
            TestScreen(
                context = context,
                navController = navController,
                totalQuestions = totalQuestions,
                correctAnswer = correctAnswer
            )
        }

        // Hasil Test Screen
        composable(
            route = "hasiltestscreen/{totalQuestions}/{correctAnswers}",
            arguments = listOf(
                navArgument("totalQuestions") { type = NavType.IntType },
                navArgument("correctAnswers") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val totalQuestions = backStackEntry.arguments?.getInt("totalQuestions") ?: 0
            val correctAnswers = backStackEntry.arguments?.getInt("correctAnswers") ?: 0

            HasilTestScreen(
                totalQuestions = totalQuestions,
                correctAnswers = correctAnswers,
                onBackClicked = {
                    navController.navigate("home") {
                        popUpTo("hasiltestscreen/{totalQuestions}/{correctAnswers}") { inclusive = true }
                    }
                },
                onDokterClicked = { navController.navigate("doctor") },
                navController = navController,
                viewModel = viewModel
            )
        }

        // Doctor Screen
        composable("doctor") {
            DoctorScreen(
                onBackClicked = { navController.popBackStack() }
            )
        }

        // Color Blind Info Screen
        composable("info") {
            ColorBlindInfoScreen(
                onBackClicked = { navController.popBackStack() },
                onInfoClicked = { category ->
                    // Implementasi lebih lanjut untuk kategori
                }
            )
        }

        // Riwayat Hasil Screen
        composable(
            route = "riwayathasil/{totalQuestions}/{correctAnswers}",
            arguments = listOf(
                navArgument("totalQuestions") { type = NavType.IntType },
                navArgument("correctAnswers") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val totalQuestions = backStackEntry.arguments?.getInt("totalQuestions") ?: 0
            val correctAnswers = backStackEntry.arguments?.getInt("correctAnswers") ?: 0

            RiwayatHasilScreen(
                navController = navController,
                viewModel = viewModel,
                totalQuestions = totalQuestions,
                correctAnswers = correctAnswers
            )
        }

        // **Riwayat Detail Screen**
        composable(
            route = "riwayat_detail/{totalPercobaan}/{benar}/{salah}/{timestamp}",
            arguments = listOf(
                navArgument("totalPercobaan") { type = NavType.IntType },
                navArgument("benar") { type = NavType.IntType },
                navArgument("salah") { type = NavType.IntType },
                navArgument("timestamp") { type = NavType.LongType }
            )
        ) { backStackEntry ->
            RiwayatDetailScreen(
                totalPercobaan = backStackEntry.arguments?.getInt("totalPercobaan") ?: 0,
                benar = backStackEntry.arguments?.getInt("benar") ?: 0,
                salah = backStackEntry.arguments?.getInt("salah") ?: 0,
                timestamp = backStackEntry.arguments?.getLong("timestamp") ?: 0L,
                navController = navController,
                viewModel = viewModel
            )
        }
    }
}
