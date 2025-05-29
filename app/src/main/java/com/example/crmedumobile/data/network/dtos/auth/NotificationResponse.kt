package com.example.crmedumobile.data.network.dtos.auth

import com.example.crmedumobile.domain.model.Notification

data class NotificationResponse(
    val notifications: List<Notification>
)
