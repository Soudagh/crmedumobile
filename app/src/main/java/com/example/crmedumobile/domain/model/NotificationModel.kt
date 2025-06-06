package com.example.crmedumobile.domain.model

import com.example.crmedumobile.domain.model.enums.NotificationType

data class NotificationModel(
    val id: Int,
    val title: String,
    val description: String,
    val date: String? = null,
    val type: NotificationType
)
