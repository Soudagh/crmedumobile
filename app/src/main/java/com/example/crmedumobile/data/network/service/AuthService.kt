package com.example.crmedumobile.data.network.service

import com.example.crmedumobile.data.network.dtos.auth.AuthRequest
import com.example.crmedumobile.data.network.dtos.auth.AuthResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {

    @POST("auth/login")
    suspend fun login(@Body authRequest: AuthRequest): AuthResponse
}