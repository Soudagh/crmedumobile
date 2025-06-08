package com.example.crmedumobile.domain.model

import com.example.crmedumobile.domain.model.enums.AttendanceStatus
import com.example.crmedumobile.domain.model.enums.LessonStatus

data class Lesson(
    val id: Long,
    val groupLesson: GroupLesson,
    val status: LessonStatus,
    val notes: String,
    val isPaid: Boolean,
    val attendanceStatus: AttendanceStatus
)
