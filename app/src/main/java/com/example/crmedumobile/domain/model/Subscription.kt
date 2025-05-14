package com.example.crmedumobile.domain.model

import java.time.LocalDate

data class Subscription(
    val id: Long,
    val program: Program,
    val subject: Subject,
    val student: Student,
    val remainingLessons: Int,
    val assignmentDate: LocalDate,
)
