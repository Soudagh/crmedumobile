package com.example.crmedumobile.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.crmedumobile.presentation.navigation.RootNavGraph
import com.example.crmedumobile.presentation.theme.CrmedumobileTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CrmedumobileTheme(darkTheme = false) {
                RootNavGraph()
            }
        }
    }
}