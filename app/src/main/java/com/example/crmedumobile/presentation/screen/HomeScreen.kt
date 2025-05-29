package com.example.crmedumobile.presentation.screen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.crmedumobile.presentation.components.BottomNavigationBar
import com.example.crmedumobile.presentation.navigation.HomeScreenNavigation

@Composable
fun HomeScreen() {
    HomeScreenContent()
}

@Composable
fun HomeScreenContent(modifier: Modifier = Modifier) {
    val controller = rememberNavController()
    Scaffold(bottomBar = {
        BottomNavigationBar(modifier = modifier.fillMaxWidth(), controller = controller)
    }) {
        HomeScreenNavigation(modifier = modifier.padding(it), controller = controller)
    }
}