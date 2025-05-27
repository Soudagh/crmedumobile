package com.example.crmedumobile.data.network.service

import com.example.crmedumobile.data.network.dtos.auth.UserResponse
import retrofit2.http.GET

interface UserService {

    @GET("users/me")
    suspend fun me(): UserResponse
}