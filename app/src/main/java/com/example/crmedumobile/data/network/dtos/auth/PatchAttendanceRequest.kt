package com.example.crmedumobile.data.network.dtos.auth

import com.example.crmedumobile.domain.model.enums.AttendanceStatusEnum

data class PatchAttendanceRequest(
    val attendanceStatus: AttendanceStatusEnum
)