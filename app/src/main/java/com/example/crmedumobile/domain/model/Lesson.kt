package com.example.crmedumobile.domain.model

import com.example.crmedumobile.domain.model.enums.LessonStatus
import java.time.LocalDate
import java.time.ZonedDateTime

data class Lesson(
    val id: Long,
    val subject: Subject,
    val program: Program,
    val tutor: Tutor,
    val notes: String,
    val lessonStatus: LessonStatus,
    val startTime: ZonedDateTime,
    val endTime: ZonedDateTime,
    val date: LocalDate,
    val link: String
)
