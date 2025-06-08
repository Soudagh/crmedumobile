package com.example.crmedumobile.data.network.repository

import com.example.crmedumobile.data.network.mapper.auth.toDomain
import com.example.crmedumobile.data.network.mapper.auth.toRequest
import com.example.crmedumobile.data.network.service.AuthService
import com.example.crmedumobile.domain.model.Auth
import com.example.crmedumobile.domain.model.Jwt
import com.example.crmedumobile.domain.repository.auth.AuthRepository

class AuthRepositoryImpl(
    private val authService: AuthService
) : AuthRepository {
    override suspend fun login(auth: Auth): Jwt = authService.login(auth.toRequest()).toDomain()
}