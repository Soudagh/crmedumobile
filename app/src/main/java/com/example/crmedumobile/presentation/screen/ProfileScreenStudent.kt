package com.example.crmedumobile.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.crmedumobile.R
import com.example.crmedumobile.domain.model.ProfileData
import com.example.crmedumobile.presentation.state.UserUiState
import com.example.crmedumobile.presentation.theme.BoldMontserrat36
import com.example.crmedumobile.presentation.viewmodel.UserViewModel

@Composable
fun ProfileScreenStudent(
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
            ProfileScreenStudentContent(
                profileData = state.profile,
                onToggleNotifications = { isEnabled ->
                    println(isEnabled)
                    viewModel.changeNotifyMode(isEnabled)
                },
                onNavigate = { navController.navigate("payment") },
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
fun ProfileScreenStudentContent(
    profileData: ProfileData,
    onToggleNotifications: (Boolean) -> Unit,
    onNavigate: () -> Unit,
    onLogout: () -> Unit,
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            stringResource(R.string.schedule),
            fontSize = 28.sp,
            color = Black,
            style = BoldMontserrat36,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        HorizontalDivider()
        Text(text = "ФИО: ${profileData.fullName}")
        Text(text = "Статус: ${profileData.role}")

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Уведомления:")
            Switch(
                checked = profileData.notifications,
                onCheckedChange = onToggleNotifications
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = onNavigate) {
            Text("Оплата")
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = onLogout,
            colors = ButtonDefaults.buttonColors(Color.LightGray)
        ) {
            Text("Выйти")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenStudentPreview() {
    ProfileScreenStudentContent(
        profileData = ProfileData(
            role = "Студент",
            fullName = "Губанова Елена",
            notifications = true
        ),
        onToggleNotifications = {},
        onLogout = {},
        onNavigate = {}
    )
}