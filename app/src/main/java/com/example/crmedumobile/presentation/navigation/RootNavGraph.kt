package com.example.crmedumobile.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.crmedumobile.presentation.screen.LoginScreen
import com.example.crmedumobile.presentation.screen.ScheduleScreen

@Composable
fun RootNavGraph(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = "schedule"
    ) {
        composable("login") {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate("home") {
                        popUpTo("login") { inclusive = true }
                    }
                }
            )
        }

        composable("home") {
//            HomeScreen()
        }

        composable("schedule") {
            ScheduleScreen(controller = navController)
        }
    }
}