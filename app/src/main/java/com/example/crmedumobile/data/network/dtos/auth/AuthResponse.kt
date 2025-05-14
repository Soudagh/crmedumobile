package com.example.crmedumobile.data.network.dtos.auth

data class AuthResponse(
    val type: String,
    val accessToken: String,
    val refreshToken: String
)

