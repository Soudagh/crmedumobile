package com.example.crmedumobile.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.crmedumobile.R
import com.example.crmedumobile.presentation.states.Screen
import com.example.crmedumobile.presentation.theme.Purple

@Composable
fun BottomTabBar(
    selectedScreen: Screen,
    onScreenSelected: (Screen) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(85.dp)
            .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
            .background(Purple)
            .padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.note_icon),
            contentDescription = "Notes",
            tint = if (selectedScreen == Screen.NOTES) Color.White else Color.Black,
            modifier = Modifier
                .size(36.dp)
                .clickable { onScreenSelected(Screen.NOTES) }
        )
        Icon(
            painter = painterResource(id = R.drawable.icon_font_bell),
            contentDescription = "Notifications",
            tint = if (selectedScreen == Screen.NOTIFICATIONS) Color.White else Color.Black,
            modifier = Modifier
                .size(36.dp)
                .clickable { onScreenSelected(Screen.NOTIFICATIONS) }
        )
        Icon(
            painter = painterResource(id = R.drawable.icon_font_calendar_clock),
            contentDescription = "Calendar",
            tint = if (selectedScreen == Screen.CALENDAR) Color.White else Color.Black,
            modifier = Modifier
                .size(36.dp)
                .clickable { onScreenSelected(Screen.CALENDAR) }
        )
        Icon(
            painter = painterResource(id = R.drawable.icon_font_user),
            contentDescription = "Profile",
            tint = if (selectedScreen == Screen.PROFILE) Color.White else Color.Black,
            modifier = Modifier
                .size(36.dp)
                .clickable { onScreenSelected(Screen.PROFILE) }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BottomTabBarPreview() {
    BottomTabBar(selectedScreen = Screen.NOTES, onScreenSelected = {})
}