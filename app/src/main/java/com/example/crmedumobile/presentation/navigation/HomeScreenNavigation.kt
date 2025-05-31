package com.example.crmedumobile.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.crmedumobile.presentation.screen.ElementsScreen
import com.example.crmedumobile.presentation.screen.HomeWorkScreen
import com.example.crmedumobile.presentation.screen.NotificationsScreen
import com.example.crmedumobile.presentation.screen.ProfileScreen
import com.example.crmedumobile.presentation.screen.ScheduleScreen
import com.example.crmedumobile.presentation.screen.ScienceScreen
import com.example.crmedumobile.presentation.screen.TheoryScreen

@Composable
fun HomeScreenNavigation(modifier: Modifier = Modifier, controller: NavHostController) {
    NavHost(controller, startDestination = "homeWork", modifier = modifier.fillMaxSize()) {
        composable("homeWork") {
            HomeWorkScreen(controller = controller)
        }
        composable("notifications") {
            NotificationsScreen()
        }
        composable("schedule") {
            ScheduleScreen(controller)
        }
        composable("profile") {
            ProfileScreen()
        }
        composable("theory") {
            TheoryScreen(controller = controller)
        }
        composable("elements") {
            ElementsScreen(controller = controller)
        }
        composable(
            "science/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) {
            ScienceScreen(controller = controller, backStackEntry = it)
        }
    }
}