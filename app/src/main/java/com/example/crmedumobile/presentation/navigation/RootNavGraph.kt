package com.example.crmedumobile.presentation.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.crmedumobile.presentation.screen.HomeScreenStudent
import com.example.crmedumobile.presentation.screen.HomeScreenTutor
import com.example.crmedumobile.presentation.screen.LoginScreen
import com.example.crmedumobile.presentation.viewmodel.SplashViewModel

@Composable
fun RootNavGraph(
    navController: NavHostController = rememberNavController(),
    splashViewModel: SplashViewModel = hiltViewModel()
) {
    val startDestinationState by splashViewModel.startDestination.collectAsState()

    val destination = startDestinationState
    if (destination == null) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
        return
    }

    NavHost(
        navController = navController,
        startDestination = destination
    ) {
        composable("login") {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate("student_nav") {
                        popUpTo("login") { inclusive = true }
                    }
                }
            )
        }

        navigation(
            route = "student_nav",
            startDestination = "student_home"
        ) {
            composable("student_home") {
                HomeScreenStudent(navController)
            }
        }

        navigation(
            route = "tutor_nav",
            startDestination = "tutor_home"
        ) {
            composable("tutor_home") {
                HomeScreenTutor(navController)
            }
        }
    }
}




