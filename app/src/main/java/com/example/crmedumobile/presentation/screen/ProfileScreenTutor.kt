package com.example.crmedumobile.presentation.screen

import LocalDimensions
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.crmedumobile.presentation.components.BottomTabBar
import com.example.crmedumobile.presentation.components.LogoutButton
import com.example.crmedumobile.presentation.components.Notification
import com.example.crmedumobile.presentation.components.ProfileItem
import com.example.crmedumobile.presentation.states.Screen
import com.example.crmedumobile.presentation.theme.*
import com.example.crmedumobile.presentation.viewmodel.ProfileData
import com.example.crmedumobile.presentation.viewmodel.ProfileViewModel

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = hiltViewModel(),
    onNavigate: (Screen) -> Unit
) {
    val profileData = viewModel.getProfileData()
    ProfileScreenContent(
        profileData = profileData,
        onToggleNotifications = { viewModel.toggleNotifications(it) },
        onNavigate = onNavigate
    )
}

@Composable
fun ProfileScreenContent(
    profileData: ProfileData,
    onToggleNotifications: (Boolean) -> Unit,
    onNavigate: (Screen) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .safeDrawingPadding()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = LocalDimensions.current.horizontalMedium),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Профиль",
                style = SemiBoldMontserrat32,
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = LocalDimensions.current.verticalSmall)
            )
        }

        Divider(
            color = DarkPurple,
            thickness = 1.dp,
            modifier = Modifier.fillMaxWidth()
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(horizontal = LocalDimensions.current.horizontalMedium),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(LocalDimensions.current.horizontalMedium))

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
            Spacer(modifier = Modifier.height(16.dp))
            ProfileItem(
                label = "Статус",
                value = profileData.status,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(LocalDimensions.current.horizontalMedium))

            Notification(
                isEnabled = profileData.notifications,
                onToggle = onToggleNotifications,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(32.dp))

            LogoutButton(
                onClick = { /* TODO: Добавить навигацию */ }
            )

            Spacer(modifier = Modifier.weight(1f))
        }

        BottomTabBar(
            selectedScreen = Screen.PROFILE,
            onScreenSelected = onNavigate
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    CrmedumobileTheme {
        ProfileScreenContent(
            profileData = ProfileData(
                role = "Преподаватель",
                fullName = "Губанова Елена",
                status = "Активен",
                notifications = true
            ),
            onToggleNotifications = {},
            onNavigate = {}
        )
    }
}