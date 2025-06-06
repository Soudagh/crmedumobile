package com.example.crmedumobile.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.crmedumobile.R
import com.example.crmedumobile.presentation.theme.BoldMontserrat36
import com.example.crmedumobile.presentation.viewmodel.ProfileViewModel

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    controller: NavHostController
) {
    val viewModel: ProfileViewModel = hiltViewModel()
    val user by viewModel.userProfile.collectAsState()

    Column(modifier = modifier.fillMaxSize()) {
        Text(stringResource(R.string.schedule), fontSize = 28.sp, color = Black, style = BoldMontserrat36, modifier = modifier.fillMaxWidth(), textAlign = TextAlign.Center)
        HorizontalDivider()
        Text(text = "ФИО: ${user.fullName}")
        Text(text = "Статус: ${user.status}")

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Уведомления:")
            Switch(
                checked = user.notificationsEnabled,
                onCheckedChange = viewModel::toggleNotifications
            )
        }

        Spacer(modifier = modifier.height(8.dp))

        Button(onClick = {
            controller.navigate("payment")
        }) {
            Text("Оплата")
        }

        Spacer(modifier = modifier.height(24.dp))

        Button(onClick = { viewModel.logout() }, colors = ButtonDefaults.buttonColors(Color.LightGray)) {
            Text("Выйти")
        }
    }
}