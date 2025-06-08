package com.example.crmedumobile.domain.usecase

import com.example.crmedumobile.domain.repository.auth.AuthRepository

class LogOutUseCase(private val authRepository: AuthRepository) {
    operator fun invoke() = authRepository.logout()
}