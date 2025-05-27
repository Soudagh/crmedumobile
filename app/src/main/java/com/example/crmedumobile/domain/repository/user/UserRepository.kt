package com.example.crmedumobile.domain.repository.user

import com.example.crmedumobile.domain.model.User

interface UserRepository {

    suspend fun profile(): User
}