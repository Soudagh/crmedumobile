package com.example.crmedumobile.presentation.states

import com.example.crmedumobile.domain.model.Notification

sealed class NotificationsUiState() {
    object Idle : NotificationsUiState()
    object Loading : NotificationsUiState()
    data class Success(val notifications: List<Notification>) : NotificationsUiState()
    data class Error(val message: String) : NotificationsUiState()
}

