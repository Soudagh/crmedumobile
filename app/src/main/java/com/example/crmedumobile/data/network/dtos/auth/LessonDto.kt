package com.example.crmedumobile.data.network.dtos.auth

import com.example.crmedumobile.domain.model.AttendanceStatus
import com.example.crmedumobile.domain.model.Program
import com.example.crmedumobile.domain.model.Subject
import com.example.crmedumobile.domain.model.Tutor
import com.example.crmedumobile.domain.model.enums.LessonStatus
import java.time.LocalDate
import java.time.ZonedDateTime

data class LessonDto(
    val id: Long,
    val subject: Subject,
    val program: Program,
    val tutor: Tutor,
    val notes: String?,
    val lessonStatus: LessonStatus,
    val startTime: ZonedDateTime,
    val endTime: ZonedDateTime,
    val date: LocalDate,
    val link: String?,
    val color: String,
    val attendances: List<AttendanceStatus>
)
