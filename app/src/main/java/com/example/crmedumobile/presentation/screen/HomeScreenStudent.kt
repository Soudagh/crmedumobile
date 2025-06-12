package com.example.crmedumobile.presentation.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.crmedumobile.presentation.components.BottomNavigationBar
import com.example.crmedumobile.presentation.navigation.studentNavGraph

@Composable
fun HomeScreenStudent(navHostController: NavHostController) {
    val innerNavController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                controller = innerNavController
            )
        }
    ) { padding ->
        NavHost(
            navController = innerNavController,
            startDestination = "homeWork",
            modifier = Modifier.padding(padding)
        ) {
            studentNavGraph(navHostController)
        }
    }
}