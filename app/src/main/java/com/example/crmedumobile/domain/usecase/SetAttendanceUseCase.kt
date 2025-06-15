package com.example.crmedumobile.domain.usecase

import com.example.crmedumobile.domain.model.enums.AttendanceStatusEnum
import com.example.crmedumobile.domain.repository.attendance.AttendanceRepository

class SetAttendanceUseCase(private val attendanceRepository: AttendanceRepository) {
    suspend operator fun invoke(attendanceStatus: AttendanceStatusEnum, id: Long) =
        attendanceRepository.setAttendanceStatusById(id, attendanceStatus)
}