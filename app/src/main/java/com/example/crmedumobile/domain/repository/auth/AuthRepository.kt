package com.example.crmedumobile.domain.repository.auth

import com.example.crmedumobile.domain.model.Auth
import com.example.crmedumobile.domain.model.Jwt

interface AuthRepository {
    suspend fun login(auth: Auth): Jwt

    fun isLoggedIn(): Boolean

    fun getRole(): String

    fun logout()
}