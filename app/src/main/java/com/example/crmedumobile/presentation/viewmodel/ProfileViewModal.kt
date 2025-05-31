package com.example.crmedumobile.presentation.viewmodel

import androidx.lifecycle.ViewModel
import javax.inject.Inject

data class ProfileData(
    val role: String,
    val fullName: String,
    val status: String,
    val notifications: Boolean
)

open class ProfileViewModel @Inject constructor() : ViewModel() {
    open fun getProfileData(): ProfileData {
        // TODO: Нужна логика, Кристина
        return ProfileData(
            role = "Преподаватель",
            fullName = "Губанова Елена",
            status = "Активен",
            notifications = true
        )
    }

    open fun toggleNotifications(enabled: Boolean) {
        // TODO: логика переключения уведомления
    }
}