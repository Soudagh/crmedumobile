package com.example.crmedumobile.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.crmedumobile.domain.model.ProfileData
import com.example.crmedumobile.presentation.components.LogoutButton
import com.example.crmedumobile.presentation.components.Notification
import com.example.crmedumobile.presentation.components.ProfileItem
import com.example.crmedumobile.presentation.state.UserUiState
import com.example.crmedumobile.presentation.theme.DarkPurple
import com.example.crmedumobile.presentation.theme.SemiBoldMontserrat32
import com.example.crmedumobile.presentation.theme.paddingButton
import com.example.crmedumobile.presentation.theme.paddingMedium
import com.example.crmedumobile.presentation.theme.paddingSmall
import com.example.crmedumobile.presentation.viewmodel.UserViewModel

@Composable
fun ProfileScreen(
    viewModel: UserViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val userState by viewModel.userState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.profile()
    }

    when (val state = userState) {
        is UserUiState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        is UserUiState.Success -> {
            ProfileScreenContent(
                profileData = state.profile,
                onToggleNotifications = { isEnabled ->
                    println(isEnabled)
                    viewModel.changeNotifyMode(isEnabled)
                },
                onLogout = {
                    viewModel.logout()
                    navController.navigate("login") {
                        popUpTo(0) { inclusive = true }
                    }
                }
            )
        }

        is UserUiState.Error -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Ошибка: ${state.message}")
            }
        }

        else -> {}
    }
}


@Composable
fun ProfileScreenContent(
    profileData: ProfileData,
    onToggleNotifications: (Boolean) -> Unit,
    onLogout: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .safeDrawingPadding()
            .padding(horizontal = paddingMedium),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Профиль",
            style = SemiBoldMontserrat32,
            color = Color.Black,
            modifier = Modifier.padding(bottom = paddingSmall)
        )
        Divider(
            color = DarkPurple,
            thickness = 1.dp,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(paddingMedium))

        ProfileItem(
            label = "Роль",
            value = profileData.role,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        ProfileItem(
            label = "Фио",
            value = profileData.fullName,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(paddingMedium))

        Notification(
            isEnabled = profileData.notifications,
            onToggle = onToggleNotifications,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(paddingButton))

        LogoutButton(
            onClick = onLogout,
        )

        Spacer(modifier = Modifier.weight(1f))

    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreenContent(
        profileData = ProfileData(
            role = "Преподаватель",
            fullName = "Губанова Елена",
            notifications = true
        ),
        onToggleNotifications = {},
        onLogout = {}
    )
}