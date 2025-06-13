package com.example.crmedumobile.presentation.states.forNotificationScheduler

data class ScheduleItemData(
    val id: Long,
    val time: String,
    val name: String,
    val type: String,
    val participant: String,
    val color: String,
    val dateTime: String,
    val attendanceStatus: String = "Присутствовал"
)