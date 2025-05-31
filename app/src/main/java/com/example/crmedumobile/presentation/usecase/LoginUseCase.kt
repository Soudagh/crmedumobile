package com.example.crmedumobile.presentation.usecase

import com.example.crmedumobile.domain.model.Auth
import com.example.crmedumobile.domain.model.Jwt
import com.example.crmedumobile.domain.repository.auth.AuthRepository

class LoginUseCase(private val authRepository: AuthRepository) {
    suspend operator fun invoke(auth: Auth): Jwt = authRepository.login(auth)
}