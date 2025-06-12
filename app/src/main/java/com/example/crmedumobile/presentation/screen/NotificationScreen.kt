package com.example.crmedumobile.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.crmedumobile.R
import com.example.crmedumobile.domain.model.Notification
import com.example.crmedumobile.presentation.components.NotificationItem
import com.example.crmedumobile.presentation.state.LoginUiState
import com.example.crmedumobile.presentation.state.UserUiState
import com.example.crmedumobile.presentation.states.NotificationsUiState
import com.example.crmedumobile.presentation.theme.BoldMontserrat36
import com.example.crmedumobile.presentation.viewmodel.AuthViewModel
import com.example.crmedumobile.presentation.viewmodel.NotificationViewModel
import kotlinx.coroutines.launch

@Composable
fun NotificationScreen(
    viewModel: NotificationViewModel = hiltViewModel(),
) {
    val notificationState by viewModel.notificationsState.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.loadNotifications()
    }

    when (val state = notificationState) {
        is NotificationsUiState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        is NotificationsUiState.Success -> {
            NotificationScreenContent(state.notifications)
        }

        is NotificationsUiState.Error -> {}

        else -> {}
    }
}

@Composable
fun NotificationScreenContent(
    notifications: List<Notification>
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            stringResource(R.string.notifications),
            fontSize = 28.sp,
            color = Black,
            style = BoldMontserrat36,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        HorizontalDivider()

        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp)) {
            itemsIndexed(notifications) { _, notification ->
                NotificationItem(notification = notification)
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NotificationsScreenPreview() {
    NotificationScreen()
}