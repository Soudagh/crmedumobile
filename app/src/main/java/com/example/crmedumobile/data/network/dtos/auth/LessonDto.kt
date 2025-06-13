package com.example.crmedumobile.data.network.dtos.auth

import com.example.crmedumobile.domain.model.enums.LessonStatus
import java.time.LocalDate
import java.time.ZonedDateTime

data class LessonDto(
    val id: Long,
    val subjectId: Long,
    val programId: Long,
    val tutorId: Long,
    val notes: String,
    val lessonStatus: LessonStatus,
    val startTime: ZonedDateTime,
    val endTime: ZonedDateTime,
    val date: LocalDate,
    val link: String,
    val color: String,
)
