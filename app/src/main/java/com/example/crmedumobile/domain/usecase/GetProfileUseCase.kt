package com.example.crmedumobile.domain.usecase

import com.example.crmedumobile.domain.model.User
import com.example.crmedumobile.domain.repository.user.UserRepository

class GetProfileUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke(): User = userRepository.profile()


}