package com.example.crmedumobile.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.crmedumobile.presentation.theme.DarkPurple
import com.example.crmedumobile.presentation.theme.RegularMontserrat24

@Composable
fun Notification(
    isEnabled: Boolean,
    onToggle: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
    ) {
        Text(
            text = "Уведомления: ",
            style = RegularMontserrat24,
            color = Black
        )

        Text(
            text = "Вкл",
            style = RegularMontserrat24,
            color = if (isEnabled) DarkPurple else Black,
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .clickable { onToggle(true) },
            fontWeight = if (isEnabled) FontWeight.Bold else FontWeight.Normal
        )

        Text(
            text = "/",
            style = RegularMontserrat24,
            color = Black
        )

        Text(
            text = "Выкл",
            style = RegularMontserrat24,
            color = if (!isEnabled) DarkPurple else Black,
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .clickable { onToggle(false) },
            fontWeight = if (!isEnabled) FontWeight.Bold else FontWeight.Normal
        )
    }
}

@Preview(showBackground = true)
@Composable
fun NotificationTogglePreview() {
    val isEnabled = remember { mutableStateOf(true) }
    Notification(
        isEnabled = isEnabled.value,
        onToggle = { isEnabled.value = it }
    )
}