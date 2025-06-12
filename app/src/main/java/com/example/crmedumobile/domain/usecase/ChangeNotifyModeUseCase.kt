package com.example.crmedumobile.domain.usecase

import com.example.crmedumobile.domain.repository.user.UserRepository

class ChangeNotifyModeUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke(newMode: Boolean) = userRepository.changeNotifyMode(newMode)
}