package com.example.crmedumobile.domain.model

import com.example.crmedumobile.domain.model.enum.UserRole

data class User(
    val id: Long,
    val surname: String,
    val name: String,
    val patronymic: String,
    val mail: String,
    val phone: String,
    val timezone: String,
    val role: UserRole,
    val notificationsEnabled: Boolean
)