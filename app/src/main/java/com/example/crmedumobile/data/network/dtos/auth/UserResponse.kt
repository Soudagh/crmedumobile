package com.example.crmedumobile.data.network.dtos.auth

import com.example.crmedumobile.domain.model.enums.UserRole
import com.example.crmedumobile.domain.model.enums.UserStatus

data class UserResponse(
    val id: Long,
    val surname: String,
    val name: String,
    val patronymic: String,
    val email: String,
    val phone: String,
    val timezone: String,
    val role: UserRole,
    val status: UserStatus,
    val notificationsEnabled: Boolean
)
