package com.example.crmedumobile.domain.model

<<<<<<< HEAD
import com.example.crmedumobile.domain.model.enums.AttendanceStatus
import com.example.crmedumobile.domain.model.enums.LessonStatus

data class Lesson(
    val id: Long,
    val groupLesson: GroupLesson,
    val status: LessonStatus,
    val notes: String,
    val isPaid: Boolean,
    val attendanceStatus: AttendanceStatus
=======
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
>>>>>>> 7e266e1b99b341a8fad2a20e4a6e8ab033d91a41
)
