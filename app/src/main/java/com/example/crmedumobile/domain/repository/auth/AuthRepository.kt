package com.example.crmedumobile.domain.repository.auth

import com.example.crmedumobile.domain.model.Auth
import com.example.crmedumobile.domain.model.Jwt

interface AuthRepository {
    suspend fun login(auth: Auth): Jwt
<<<<<<< HEAD
=======

    fun isLoggedIn(): Boolean

    fun getRole(): String
>>>>>>> 7e266e1b99b341a8fad2a20e4a6e8ab033d91a41
}