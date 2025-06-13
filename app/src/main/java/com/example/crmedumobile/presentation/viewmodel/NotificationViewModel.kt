package com.example.crmedumobile.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crmedumobile.domain.model.Notification
import com.example.crmedumobile.domain.model.NotificationType
import com.example.crmedumobile.domain.usecase.GetNotificationsUseCase
import com.example.crmedumobile.presentation.states.NotificationsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.ZonedDateTime
import javax.inject.Inject

@HiltViewModel
class NotificationViewModel @Inject constructor(
    private val notificationUseCase: GetNotificationsUseCase
): ViewModel() {

    private val _notificationsState = MutableStateFlow<NotificationsUiState>(NotificationsUiState.Idle)
    val notificationsState: StateFlow<NotificationsUiState> = _notificationsState

//    fun loadNotifications() {
//        viewModelScope.launch {
//            _notificationsState.value = NotificationsUiState.Loading
//            try {
//                val notifications = notificationUseCase()
//                _notificationsState.value = NotificationsUiState.Success(notifications)
//            } catch (e: Exception) {
//                _notificationsState.value = NotificationsUiState.Error(e.message ?: "Ошибка загрузки уведомлений")
//            }
//        }
//    }

    fun loadNotifications() {
        viewModelScope.launch {
            _notificationsState.value = NotificationsUiState.Loading

            // ✅ Моки
            val mockNotifications = listOf(
                Notification(
                    id = 1L,
                    title = "Новое ДЗ по математике",
                    description = "Загрузите до пятницы",
                    link = "https://example.com/homework/1",
                    notificationType = NotificationType.HOMEWORK,
                    createdAt = ZonedDateTime.now().minusDays(1),
                    isRead = false
                ),
                Notification(
                    id = 2L,
                    title = "Изменение в расписании",
                    description = "Физика перенесена на субботу",
                    link = null,
                    notificationType = NotificationType.LESSON,
                    createdAt = ZonedDateTime.now().minusDays(2),
                    isRead = true
                ),
                Notification(
                    id = 3L,
                    title = "Платёж получен",
                    description = "",
                    link = null,
                    notificationType = NotificationType.PAYMENT,
                    createdAt = ZonedDateTime.now().minusDays(3),
                    isRead = true
                )
            )

            _notificationsState.value = NotificationsUiState.Success(mockNotifications)
        }
    }
}
