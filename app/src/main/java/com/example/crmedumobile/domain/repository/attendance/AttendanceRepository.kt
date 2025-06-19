package com.example.crmedumobile.domain.repository.attendance

import com.example.crmedumobile.domain.model.enums.AttendanceStatusEnum

interface AttendanceRepository {
    suspend fun setAttendanceStatusById(id: Long, attendanceStatus: AttendanceStatusEnum)

    suspend fun markAttendance(qrPayload: String)
}