package com.example.crmedumobile.data.network.service

import com.example.crmedumobile.data.network.dtos.auth.NotificationResponse
import retrofit2.http.GET

interface NotificationService {

    @GET("notifications")
    suspend fun getNotifications(): NotificationResponse
}