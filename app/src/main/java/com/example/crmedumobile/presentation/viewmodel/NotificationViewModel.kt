package com.example.crmedumobile.presentation.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.crmedumobile.domain.model.NotificationModel
import com.example.crmedumobile.domain.model.enums.NotificationType

class NotificationViewModel : ViewModel() {

    private val _notifications = mutableStateListOf<NotificationModel>()
    val notifications: List<NotificationModel> get() = _notifications

    init {
        loadNotifications()
    }

    private fun loadNotifications() {
        _notifications.addAll(
            listOf(
                NotificationModel(
                    id = 1,
                    title = "Назначено занятие на сегодня.",
                    description = "Математика (Мария Космач). 14:00",
                    type = NotificationType.LESSON
                ),
                NotificationModel(
                    id = 2,
                    title = "Назначено ДЗ по математике.",
                    description = "Дедлайн: 18.05.2025",
                    type = NotificationType.HOMEWORK
                ),
                NotificationModel(
                    id = 3,
                    title = "Скоро нужно будет оплачивать курс по Математике.",
                    description = "",
                    type = NotificationType.PAYMENT
                )
            )
        )
    }
}
