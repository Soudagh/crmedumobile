package com.example.crmedumobile.data.network.repository

import com.example.crmedumobile.data.network.dtos.auth.UpdateNotifyRequest
import com.example.crmedumobile.data.network.mapper.user.toDomain
import com.example.crmedumobile.data.network.service.UserService
import com.example.crmedumobile.domain.model.User
import com.example.crmedumobile.domain.repository.user.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userService: UserService
) : UserRepository {
    override suspend fun profile(): User = userService.me().toDomain()

    override suspend fun changeNotifyMode(newMode: Boolean) {
        userService.changeNotifyMode(
            UpdateNotifyRequest(newMode)
        )
    }
}