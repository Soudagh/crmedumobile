package com.example.crmedumobile.data.network.mapper.user

import com.example.crmedumobile.data.network.dtos.auth.UserResponse
import com.example.crmedumobile.domain.model.User

fun UserResponse.toDomain(): User = User(
    id,
    surname,
    name,
    patronymic,
    mail = email,
    phone,
    timezone,
    role,
    notificationsEnabled
)
