package com.example.crmedumobile.data.network.mapper.schedule

import com.example.crmedumobile.data.network.dtos.auth.LessonDto
import com.example.crmedumobile.domain.model.Lesson

fun LessonDto.toDomain(): Lesson = Lesson(
    id = id,
    subject = subject,
    program = program,
    tutor = tutor,
    notes = notes,
    lessonStatus = lessonStatus,
    startTime = startTime,
    endTime = endTime,
    date = date,
    link = link,
    color = color,
    attendances = attendances
)