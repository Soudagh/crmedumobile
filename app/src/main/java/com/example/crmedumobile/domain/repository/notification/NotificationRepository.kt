package com.example.crmedumobile.domain.repository.notification

import com.example.crmedumobile.domain.model.Notification

interface NotificationRepository {
    suspend fun getNotifications(): List<Notification>
}