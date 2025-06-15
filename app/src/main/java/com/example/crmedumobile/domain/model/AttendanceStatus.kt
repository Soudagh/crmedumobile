package com.example.crmedumobile.domain.model

import com.example.crmedumobile.domain.model.enums.AttendanceStatusEnum

data class AttendanceStatus(
    val id: Long,
    val student: Student,
    val attendanceStatus: AttendanceStatusEnum
)