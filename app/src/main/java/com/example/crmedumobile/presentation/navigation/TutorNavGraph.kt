package com.example.crmedumobile.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.crmedumobile.presentation.screen.CheckHomeworkScreen
import com.example.crmedumobile.presentation.screen.ElementsScreen
import com.example.crmedumobile.presentation.screen.HomeWorkScreenTutor
import com.example.crmedumobile.presentation.screen.LessonInfoScreenTutor
import com.example.crmedumobile.presentation.screen.NotificationsScreen
import com.example.crmedumobile.presentation.screen.ProfileScreen
import com.example.crmedumobile.presentation.screen.ScheduleScreenTutor
import com.example.crmedumobile.presentation.screen.ScienceScreen
import com.example.crmedumobile.presentation.screen.StudentHomeworkScreenTutor

fun NavGraphBuilder.tutorNavGraph(navController: NavHostController) {
    composable("homeWork") {
        HomeWorkScreenTutor(controller = navController)
    }
    composable("notifications") {
        NotificationsScreen()
    }
    composable("schedule") {
        ScheduleScreenTutor(navController = navController)
    }
    composable("profile") {
        ProfileScreen("TUTOR", navController = navController)
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

    composable(
        "check_homework"
    ) {
        CheckHomeworkScreen(
            navController = navController,
            onBack = { navController.popBackStack() })
    }

    composable(
        "homework/{id}",
        arguments = listOf(navArgument("id") { type = NavType.IntType })
    ) {
        StudentHomeworkScreenTutor(
            navController = navController,
            onBack = { navController.popBackStack() })
    }

    composable(
        "lesson/{id}",
        arguments = listOf(navArgument("id") { type = NavType.IntType })
    ) {
        LessonInfoScreenTutor(navController = navController)
    }
}

