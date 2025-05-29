package com.example.crmedumobile.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation

fun NavGraphBuilder.tutorNavGraph(navController: NavHostController) {
    navigation(startDestination = "tutor_home", route = "tutor_nav") {
        composable("tutor_home") {
//            TutorHomeScreen()
        }
    }
}

