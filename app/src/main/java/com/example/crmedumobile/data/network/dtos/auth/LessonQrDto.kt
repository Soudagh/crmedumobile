package com.example.crmedumobile.data.network.dtos.auth

import java.time.ZonedDateTime

data class LessonQrDto(
    val qrPayload: String,
    val expiresAt: ZonedDateTime
)
