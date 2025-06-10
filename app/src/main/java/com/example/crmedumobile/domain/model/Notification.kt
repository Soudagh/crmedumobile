package com.example.crmedumobile.domain.model

import java.time.ZonedDateTime

data class Notification(
    val id: Long,
    val title: String,
    val description: String,
    val link: String?,
    val notificationType: NotificationType,
    val createdAt: ZonedDateTime,
    val isRead: Boolean,
)