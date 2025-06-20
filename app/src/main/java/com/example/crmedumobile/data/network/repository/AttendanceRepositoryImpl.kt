package com.example.crmedumobile.data.network.repository

import com.example.crmedumobile.data.network.dtos.auth.AttendanceQrRequest
import com.example.crmedumobile.data.network.dtos.auth.PatchAttendanceRequest
import com.example.crmedumobile.data.network.service.AttendanceService
import com.example.crmedumobile.data.network.util.ErrorParser
import com.example.crmedumobile.domain.model.enums.AttendanceStatusEnum
import com.example.crmedumobile.domain.repository.attendance.AttendanceRepository
import retrofit2.HttpException
import javax.inject.Inject

class AttendanceRepositoryImpl @Inject constructor(
    private val attendanceService: AttendanceService,
    private val errorParser: ErrorParser
) : AttendanceRepository {

    override suspend fun setAttendanceStatusById(id: Long, attendanceStatus: AttendanceStatusEnum) {
        return try {
            attendanceService.setAttendance(id, PatchAttendanceRequest(attendanceStatus))
        } catch (e: HttpException) {
            val errorMessage = errorParser.parse(e)
            println(errorMessage)
        }
    }

    override suspend fun markAttendance(qrPayload: String) {
        attendanceService.markAttendance(AttendanceQrRequest(qrPayload))
    }
}