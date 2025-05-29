package com.example.crmedumobile.domain.usecase

import com.example.crmedumobile.domain.model.Notification
import com.example.crmedumobile.domain.repository.notification.NotificationRepository

class GetNotificationsUseCase(
    private val notificationRepository: NotificationRepository
) {
    suspend operator fun invoke(): List<Notification> = notificationRepository.getNotifications()
}
