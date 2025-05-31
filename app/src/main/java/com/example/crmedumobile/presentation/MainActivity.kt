package com.example.crmedumobile.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
<<<<<<< HEAD
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.crmedumobile.presentation.screen.ProfileScreen
import com.example.crmedumobile.presentation.screen.ScheduleScreen
=======
import com.example.crmedumobile.presentation.navigation.RootNavGraph
>>>>>>> 7e266e1b99b341a8fad2a20e4a6e8ab033d91a41
import com.example.crmedumobile.presentation.theme.CrmedumobileTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
<<<<<<< HEAD
            CrmedumobileTheme {
                //RootNavGraph()
                //ProfileScreen()
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    var currentScreen by remember { mutableStateOf(com.example.crmedumobile.presentation.states.Screen.NOTES) }

    NavHost(
        navController = navController,
        startDestination = com.example.crmedumobile.presentation.states.Screen.NOTES.name
    ) {
        composable(com.example.crmedumobile.presentation.states.Screen.NOTES.name) {
            ScheduleScreen(
                onNavigate = { screen ->
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
        composable(com.example.crmedumobile.presentation.states.Screen.PROFILE.name) {
            ProfileScreen(
                onNavigate = { screen ->
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
        composable(com.example.crmedumobile.presentation.states.Screen.NOTIFICATIONS.name) {
            Text("Notifications Screen")
        }
        composable(com.example.crmedumobile.presentation.states.Screen.CALENDAR.name) {
            Text("Calendar Screen")
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CrmedumobileTheme {
        Greeting("Android")
    }
=======
            CrmedumobileTheme(darkTheme = false) {
                RootNavGraph()
            }
        }
    }
>>>>>>> 7e266e1b99b341a8fad2a20e4a6e8ab033d91a41
}