package com.example.crmedumobile.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.crmedumobile.presentation.screen.ElementsScreen
import com.example.crmedumobile.presentation.screen.FinishTaskScreen
import com.example.crmedumobile.presentation.screen.HomeWorkScreen
import com.example.crmedumobile.presentation.screen.NotificationScreen
import com.example.crmedumobile.presentation.screen.PaymentScreen
import com.example.crmedumobile.presentation.screen.PaymentSubjectScreen
import com.example.crmedumobile.presentation.screen.ProfileScreen
import com.example.crmedumobile.presentation.screen.ScheduleScreenStudent
import com.example.crmedumobile.presentation.screen.ScienceScreen
import com.example.crmedumobile.presentation.screen.TheoryScreen

fun NavGraphBuilder.studentNavGraph(controller: NavHostController) {
    composable("homeWork") {
        HomeWorkScreen(controller = controller)
    }
    composable("finishTask") {
        FinishTaskScreen(controller = controller)
    }
    composable("notifications") {
        NotificationScreen()
    }
    composable("schedule") {
        ScheduleScreenStudent(controller = controller)
    }
    composable("profile") {
        ProfileScreen("STUDENT", navController = controller)
    }
    composable("theory/{id}", arguments = listOf(navArgument("id") { type = NavType.IntType })) {
        TheoryScreen(controller = controller, backStackEntry = it)
    }
    composable("elements") {
        ElementsScreen(controller = controller)
    }
    composable("science/{id}", arguments = listOf(navArgument("id") { type = NavType.IntType })) {
        ScienceScreen(controller = controller, backStackEntry = it)
    }
    composable("payment") {
        PaymentScreen(onSubjectClick = { subject ->
            controller.navigate("subject_payment/$subject")
        })
    }
    composable(
        "subject_payment/{subject}",
        arguments = listOf(navArgument("subject") { type = NavType.StringType })
    ) {
        PaymentSubjectScreen(backStackEntry = it)
    }
}
