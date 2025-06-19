package com.example.crmedumobile.domain.usecase

import com.example.crmedumobile.domain.repository.attendance.AttendanceRepository

class MarkAttendanceByQr(private val attendanceRepository: AttendanceRepository) {
    suspend operator fun invoke(qrPayload: String) = attendanceRepository.markAttendance(qrPayload)
}