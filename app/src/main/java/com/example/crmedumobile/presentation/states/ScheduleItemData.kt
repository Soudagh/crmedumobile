package com.example.crmedumobile.presentation.states

import androidx.compose.ui.graphics.Color

enum class Screen {
    NOTES, NOTIFICATIONS, CALENDAR, PROFILE
}

data class ScheduleItemData(
    val time: String,
    val name: String,
    val type: String,
    val participant: String,
    val color: Color
)