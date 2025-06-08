package com.example.crmedumobile.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.crmedumobile.presentation.screen.ElementsScreen
import com.example.crmedumobile.presentation.screen.HomeWorkScreen
import com.example.crmedumobile.presentation.screen.NotificationsScreen
import com.example.crmedumobile.presentation.screen.ProfileScreen
import com.example.crmedumobile.presentation.screen.ScheduleScreenStudent
import com.example.crmedumobile.presentation.screen.ScienceScreen
import com.example.crmedumobile.presentation.screen.TheoryScreen

fun NavGraphBuilder.studentNavGraph(navController: NavHostController) {
    composable("homeWork") {
        HomeWorkScreen(controller = navController)
    }
    composable("notifications") {
        NotificationsScreen()
    }
    composable("schedule") {
        ScheduleScreenStudent(controller = navController)
    }
    composable("profile") {
        ProfileScreen(navController = navController)
    }
    composable("theory") {
        TheoryScreen(controller = navController)
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
