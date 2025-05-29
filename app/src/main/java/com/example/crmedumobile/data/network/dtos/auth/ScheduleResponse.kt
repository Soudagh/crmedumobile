package com.example.crmedumobile.data.network.dtos.auth

import com.example.crmedumobile.domain.model.Lesson

data class ScheduleResponse(
    val lessons: List<Lesson>
)