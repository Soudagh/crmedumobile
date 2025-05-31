package com.example.crmedumobile.presentation.navigation

<<<<<<< HEAD
import androidx.compose.runtime.Composable
=======
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
>>>>>>> 7e266e1b99b341a8fad2a20e4a6e8ab033d91a41
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
<<<<<<< HEAD
import com.example.crmedumobile.presentation.screen.LoginScreen

@Composable
fun RootNavGraph(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = "login"
=======
import androidx.navigation.navigation
import com.example.crmedumobile.presentation.screen.HomeScreenStudent
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
>>>>>>> 7e266e1b99b341a8fad2a20e4a6e8ab033d91a41
    ) {
        composable("login") {
            LoginScreen(
                onLoginSuccess = {
<<<<<<< HEAD
                    navController.navigate("home") {
=======
                    navController.navigate("student_nav") {
>>>>>>> 7e266e1b99b341a8fad2a20e4a6e8ab033d91a41
                        popUpTo("login") { inclusive = true }
                    }
                }
            )
<<<<<<< HEAD
            // TODO:
        }

        composable("home") {
//            HomeScreen()
        }
    }
}
=======
        }

        navigation(
            route = "student_nav",
            startDestination = "student_home"
        ) {
            composable("student_home") {
                HomeScreenStudent()
            }
        }

        navigation(
            route = "tutor_nav",
            startDestination = "tutor_home"
        ) {
            composable("tutor_home") {
                // TutorHomeScreen()
            }
        }
    }
}




>>>>>>> 7e266e1b99b341a8fad2a20e4a6e8ab033d91a41
