package com.example.crmedumobile.data.network.repository

import com.example.crmedumobile.data.network.service.NotificationService
import com.example.crmedumobile.domain.model.Notification
import com.example.crmedumobile.domain.repository.notification.NotificationRepository
import javax.inject.Inject

class NotificationRepositoryImpl @Inject constructor(
    private val notificationService: NotificationService
): NotificationRepository {
    override suspend fun getNotifications(): List<Notification> = notificationService.getNotifications().notifications
}
