package com.example.crmedumobile.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.crmedumobile.presentation.screen.CheckHomeworkScreen
import com.example.crmedumobile.presentation.screen.HomeWorkScreen
import com.example.crmedumobile.presentation.screen.HomeworkItem
import com.example.crmedumobile.presentation.screen.NotificationsScreen
import com.example.crmedumobile.presentation.screen.ProfileScreen
import com.example.crmedumobile.presentation.screen.ScheduleScreen
import com.example.crmedumobile.presentation.screen.StudentHomeworkScreen
import com.example.crmedumobile.presentation.screen.SubjectInfoScreen
import com.example.crmedumobile.presentation.states.Screen
import com.example.crmedumobile.presentation.states.forNotificationScheduler.ScheduleItemData
import com.example.crmedumobile.presentation.theme.SubjectColors
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Composable
fun RootNavGraph(
    navController: NavHostController = rememberNavController()
) {
    var currentScreen: Screen by remember { mutableStateOf(Screen.CALENDAR) }

    NavHost(
        navController = navController,
        startDestination = Screen.CALENDAR.name
    ) {
        composable(Screen.CALENDAR.name) {
            ScheduleScreen(
                onNavigate = { screen: Screen ->
                    currentScreen = screen
                    navController.navigate(screen.name) {
                        popUpTo(navController.graph.startDestinationId) {
                            inclusive = false
                        }
                        launchSingleTop = true
                    }
                },
                onSubjectSelected = { subject ->
                    try {
                        val subjectJson = Json.encodeToString(subject)
                        navController.navigate("subject_info/$subjectJson")
                    } catch (e: Exception) {
                        println("Serialization error: ${e.message}")
                    }
                }
            )
        }
        composable(Screen.PROFILE.name) {
            ProfileScreen(
                onNavigate = { screen: Screen ->
                    currentScreen = screen
                    navController.navigate(screen.name) {
                        popUpTo(navController.graph.startDestinationId) {
                            inclusive = false
                        }
                        launchSingleTop = true
                    }
                }
            )
        }
        composable(Screen.NOTIFICATIONS.name) {
            NotificationsScreen(
                onNavigate = { screen: Screen ->
                    currentScreen = screen
                    navController.navigate(screen.name) {
                        popUpTo(navController.graph.startDestinationId) {
                            inclusive = false
                        }
                        launchSingleTop = true
                    }
                }
            )
        }
        composable(Screen.NOTES.name) {
            HomeWorkScreen(
                onNavigate = { screen: Screen ->
                    currentScreen = screen
                    navController.navigate(screen.name) {
                        popUpTo(navController.graph.startDestinationId) {
                            inclusive = false
                        }
                        launchSingleTop = true
                    }
                },
                onCheckHomeworkClick = {
                    navController.navigate("check_homework")
                }
            )
        }
        composable("check_homework") {
            CheckHomeworkScreen(
                onNavigate = { screen: Screen ->
                    currentScreen = screen
                    navController.navigate(screen.name) {
                        popUpTo(navController.graph.startDestinationId) {
                            inclusive = false
                        }
                        launchSingleTop = true
                    }
                },
                onBack = { navController.popBackStack() },
                onHomeworkSelected = { homeworkItem ->
                    try {
                        val homeworkJson = Json.encodeToString(homeworkItem)
                        navController.navigate("student_homework/$homeworkJson")
                    } catch (e: Exception) {
                        println("Homework serialization error: ${e.message}")
                    }
                }
            )
        }
        composable("subject_info/{subjectJson}") { backStackEntry ->
            val subjectJson = backStackEntry.arguments?.getString("subjectJson") ?: ""
            val subject = try {
                Json.decodeFromString<ScheduleItemData>(subjectJson)
            } catch (e: Exception) {
                println("Deserialization error: ${e.message}")
                ScheduleItemData(
                    id = "",
                    time = "",
                    name = "",
                    type = "",
                    participant = "",
                    color = SubjectColors[0],
                    dateTime = ""
                )
            }
            SubjectInfoScreen(
                subject = subject,
                onNavigate = { screen: Screen ->
                    currentScreen = screen
                    navController.navigate(screen.name) {
                        popUpTo(navController.graph.startDestinationId) {
                            inclusive = false
                        }
                        launchSingleTop = true
                    }
                }
            )
        }
        composable("student_homework/{homeworkJson}") { backStackEntry ->
            val homeworkJson = backStackEntry.arguments?.getString("homeworkJson") ?: ""
            val homeworkItem = try {
                Json.decodeFromString<HomeworkItem>(homeworkJson)
            } catch (e: Exception) {
                println("Homework deserialization error: ${e.message}")
                HomeworkItem("", "", "")
            }
            StudentHomeworkScreen(
                homeworkItem = homeworkItem,
                onNavigate = { screen: Screen ->
                    currentScreen = screen
                    navController.navigate(screen.name) {
                        popUpTo(navController.graph.startDestinationId) {
                            inclusive = false
                        }
                        launchSingleTop = true
                    }
                },
                onBack = { navController.popBackStack() }
            )
        }
    }
}