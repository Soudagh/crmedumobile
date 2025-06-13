package com.example.crmedumobile.domain.usecase

import com.example.crmedumobile.domain.repository.auth.AuthRepository

class GetUserRoleUseCase(private val authRepository: AuthRepository) {
    suspend operator fun invoke(): String = authRepository.getRole()
}