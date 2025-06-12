package com.example.crmedumobile.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crmedumobile.domain.usecase.GetNotificationsUseCase
import com.example.crmedumobile.presentation.states.NotificationsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotificationViewModel @Inject constructor(
    private val notificationUseCase: GetNotificationsUseCase
): ViewModel() {

    private val _notificationsState = MutableStateFlow<NotificationsUiState>(NotificationsUiState.Idle)
    val notificationsState: StateFlow<NotificationsUiState> = _notificationsState

    fun loadNotifications() {
        viewModelScope.launch {
            _notificationsState.value = NotificationsUiState.Loading
            try {
                val notifications = notificationUseCase()
                _notificationsState.value = NotificationsUiState.Success(notifications)
            } catch (e: Exception) {
                _notificationsState.value = NotificationsUiState.Error(e.message ?: "Ошибка загрузки уведомлений")
            }
        }
    }
}
