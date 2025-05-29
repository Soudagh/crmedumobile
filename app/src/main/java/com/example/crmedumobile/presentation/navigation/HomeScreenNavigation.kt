package com.example.crmedumobile.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.crmedumobile.presentation.screen.ElementsScreen
import com.example.crmedumobile.presentation.screen.HomeWorkScreen
import com.example.crmedumobile.presentation.screen.NotificationScreen
import com.example.crmedumobile.presentation.screen.ProfileScreen
import com.example.crmedumobile.presentation.screen.ScheduleScreen
import com.example.crmedumobile.presentation.screen.ScienceScreen
import com.example.crmedumobile.presentation.screen.TheoryScreen

@Composable
fun HomeScreenNavigation(modifier: Modifier = Modifier, controller: NavHostController) {
    NavHost(controller, startDestination = "homeWork", modifier = modifier.fillMaxSize()){
        composable("homeWork"){
            HomeWorkScreen(controller = controller)
        }
        composable("notifications"){
            NotificationScreen(controller = controller)
        }
        composable("schedule"){
            ScheduleScreen(controller = controller)
        }
        composable("profile"){
            ProfileScreen(controller = controller)
        }
        composable("theory") {
            TheoryScreen(controller = controller)
        }
        composable("elements") {
            ElementsScreen(controller = controller)
        }
        composable("science/{id}", arguments = listOf(navArgument("id"){ type = NavType.IntType })) {
            ScienceScreen(controller = controller, backStackEntry = it)
        }
    }
}