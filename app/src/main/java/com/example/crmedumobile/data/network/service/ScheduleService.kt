package com.example.crmedumobile.data.network.service

import com.example.crmedumobile.data.network.dtos.auth.ScheduleResponse
import retrofit2.http.GET

interface ScheduleService {

    @GET("users/me/schedule")
    suspend fun getUserSchedule(): ScheduleResponse
}