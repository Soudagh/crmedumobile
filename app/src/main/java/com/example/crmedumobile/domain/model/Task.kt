package com.example.crmedumobile.domain.model

import java.time.ZonedDateTime

data class Task(
    val id: Long,
    val lesson: Lesson,
    val title: String,
    val description: String,
    val rightAnswer: String,
    val startDate: ZonedDateTime,
    val endDateTime: ZonedDateTime
)
