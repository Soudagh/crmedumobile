package com.example.crmedumobile.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.crmedumobile.presentation.screen.ElementsScreen
import com.example.crmedumobile.presentation.screen.HomeWorkScreen
import com.example.crmedumobile.presentation.screen.NotificationScreen
import com.example.crmedumobile.presentation.screen.ProfileScreenTutor
import com.example.crmedumobile.presentation.screen.ScheduleScreenTutor
import com.example.crmedumobile.presentation.screen.ScienceScreen
import com.example.crmedumobile.presentation.screen.TheoryScreen

fun NavGraphBuilder.tutorNavGraph(navController: NavHostController) {
    composable("homeWork") {
        HomeWorkScreen(controller = navController)
    }
    composable("notifications") {
        NotificationScreen()
    }
    composable("schedule") {
        ScheduleScreenTutor(controller = navController)
    }
    composable("profile") {
        ProfileScreenTutor(navController = navController)
    }
    composable("theory/{id}", arguments = listOf(navArgument("id") { type = NavType.IntType })) {
        TheoryScreen(controller = navController, backStackEntry = it)
    }
    composable("elements") {
        ElementsScreen(controller = navController)
    }
    composable(
        "science/{id}",
        arguments = listOf(navArgument("id") { type = NavType.IntType })
    ) {
        ScienceScreen(controller = navController, backStackEntry = it)
    }
}

