package com.example.crmedumobile.presentation.states.forNotificationScheduler

import androidx.compose.ui.graphics.Color
import kotlinx.serialization.Serializable


@Serializable
data class ScheduleItemData(
    val id: String,
    val time: String,
    val name: String,
    val type: String,
    val participant: String,
    @Serializable(with = ColorSerializer::class)
    val color: Color,
    val dateTime: String,
    val attendanceStatus: String = "Присутствовал"
)