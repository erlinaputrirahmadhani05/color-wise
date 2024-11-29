package com.cipta.colorwise.ui.navigation

import android.content.Context
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

@Composable
fun NavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    viewModel: ColorWiseViewModel = viewModel()  // ViewModel untuk InputScreen
) {
    val context = LocalContext.current

    NavHost(navController = navController, startDestination = "splash", modifier = modifier) {
        // SplashScreen
        composable("splash") {
            SplashScreen(onSplashFinished = {
                // Pindah ke halaman berikutnya setelah splash screen selesai
                navController.navigate("input") {
                    popUpTo("splash") { inclusive = true }  // Hapus SplashScreen dari stack
                }
            })
        }

        // InputScreen
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

        // HomeScreen
        composable("home") {
            HomeScreen(
                navController = navController,
                viewModel = viewModel
            )
        }

        // SplashTestScreen
        composable("splashtest") {
            SplashTest(navController = navController)
        }

        // TestScreen
        composable("test") {
            val correctAnswer = "jawaban benar"
            val totalQuestions = 10
            TestScreen(
                context = context,
                navController = navController,
                totalQuestions = totalQuestions,
                correctAnswer = correctAnswer
            )
        }

        // HasilTestScreen rute
        composable(
            route = "hasiltestscreen/{totalQuestions}/{correctAnswers}",
            arguments = listOf(
                navArgument("totalQuestions") { type = NavType.IntType },
                navArgument("correctAnswers") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val totalQuestions = backStackEntry.arguments?.getInt("totalQuestions") ?: 0
            val correctAnswers = backStackEntry.arguments?.getInt("correctAnswers") ?: 0

            // Pastikan navController diteruskan ke HasilTestScreen
            HasilTestScreen(
                totalQuestions = totalQuestions,
                correctAnswers = correctAnswers,
                onBackClicked = {
                    navController.navigate("home") {
                        popUpTo("hasiltestscreen/{totalQuestions}/{correctAnswers}") { inclusive = true }
                    }
                },
                onDokterClicked = {
                    navController.navigate("doctor")
                },
                navController = navController  // Menambahkan navController yang hilang
            )
        }


        // DoctorScreen
        composable("doctor") {
            DoctorScreen(
                onBackClicked = { navController.popBackStack() }
            )
        }

        // ColorBlindInfoScreen
        composable("info") {
            ColorBlindInfoScreen(
                onBackClicked = { navController.popBackStack() },
                onInfoClicked = { category -> }
            )
        }

        // RiwayatHasilScreen
        composable(
            "riwayathasil/{totalQuestions}/{correctAnswers}",
            arguments = listOf(
                navArgument("totalQuestions") { type = NavType.IntType },
                navArgument("correctAnswers") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val totalQuestions = backStackEntry.arguments?.getInt("totalQuestions") ?: 0
            val correctAnswers = backStackEntry.arguments?.getInt("correctAnswers") ?: 0

            RiwayatHasilScreen(
                totalQuestions = totalQuestions,
                correctAnswers = correctAnswers,
                navController = navController
            )
        }

    }
}