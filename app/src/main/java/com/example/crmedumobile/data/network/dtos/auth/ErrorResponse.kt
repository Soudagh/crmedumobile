package com.example.crmedumobile.data.network.dtos.auth

data class ErrorResponse(
    val timestamp: String?,
    val status: Int?,
    val error: String?,
    val path: String?
)
