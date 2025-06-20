package com.example.crmedumobile.data.network.service

import com.example.crmedumobile.data.network.dtos.auth.AttendanceQrRequest
import com.example.crmedumobile.data.network.dtos.auth.PatchAttendanceRequest
import retrofit2.http.Body
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path


interface AttendanceService {

    @PATCH("attendances/{id}")
    suspend fun setAttendance(@Path("id") id: Long, @Body request: PatchAttendanceRequest)

    @POST("attendances/mark")
    suspend fun markAttendance(@Body request: AttendanceQrRequest)
}