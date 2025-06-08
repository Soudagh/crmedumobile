package com.example.crmedumobile.data.network.mapper.schedule

import com.example.crmedumobile.data.network.dtos.auth.LessonDto
import com.example.crmedumobile.domain.model.Lesson
import com.example.crmedumobile.domain.model.Program
import com.example.crmedumobile.domain.model.Subject
import com.example.crmedumobile.domain.model.Tutor
import com.example.crmedumobile.domain.model.User
import com.example.crmedumobile.domain.model.enums.UserRole

fun LessonDto.toDomain(): Lesson = Lesson(
    id = id,
    subject = Subject(1, "saf"),
    program = Program(1, "safas", 5, 2.66),
    tutor = Tutor(
        3,
        User(
            5,
            "asf",
            "asf",
            "asf",
            "asf",
            "asfsaf",
            "asfasf",
            UserRole.STUDENT,
            true
        ),
        listOf(),
        "safas"
    ),
    notes = notes,
    lessonStatus = lessonStatus,
    startTime = startTime,
    endTime = endTime,
    date = date,
    link = link
)