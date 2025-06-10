package com.example.crmedumobile.data.network.dtos.auth

data class ValidationErrorResponse(
    val violations: List<Violation>
)

data class Violation(
    val fieldName: String,
    val message: String
)