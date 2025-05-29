package com.example.crmedumobile.presentation.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.crmedumobile.presentation.viewmodel.NotificationViewModel

@Composable
fun NotificationsScreen(
    viewModel: NotificationViewModel = hiltViewModel(),
) {
    val notificationState by viewModel.notificationsState.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.loadNotifications()
    }
}

@Preview(showBackground = true)
@Composable
fun NotificationsScreenPreview() {
    NotificationsScreen()
}