package com.example.crmedumobile.domain.model

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.ZonedDateTime

data class GroupLesson (
    val id: Long,
    val student: Student,
    val subject: Subject,
    val tutor: Tutor,
    val dayOfWeek: DayOfWeek,
    val startPeriod: LocalDate,
    val endPeriod: LocalDate,
    val startTime: ZonedDateTime,
    val endTime: ZonedDateTime,
    val link: Link
)