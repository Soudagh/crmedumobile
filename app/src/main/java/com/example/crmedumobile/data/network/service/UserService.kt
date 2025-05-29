package com.example.crmedumobile.data.network.service

import com.example.crmedumobile.data.network.dtos.auth.UpdateNotifyRequest
import com.example.crmedumobile.data.network.dtos.auth.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH

interface UserService {

    @GET("users/me")
    suspend fun me(): UserResponse

    @PATCH("users/me")
    suspend fun changeNotifyMode(@Body request: UpdateNotifyRequest): Response<Unit>
}