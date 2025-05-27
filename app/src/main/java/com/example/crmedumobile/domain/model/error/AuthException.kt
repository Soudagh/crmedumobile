package com.example.crmedumobile.domain.model.error

class AuthException(
    override val message: String
) : Exception(message)
