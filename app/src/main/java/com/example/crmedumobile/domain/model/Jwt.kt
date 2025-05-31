package com.example.crmedumobile.domain.model

data class Jwt(
    val type: String,
    val accessToken: String,
    val refreshToken: String
)
