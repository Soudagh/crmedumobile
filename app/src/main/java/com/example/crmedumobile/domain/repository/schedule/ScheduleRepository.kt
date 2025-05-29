package com.example.crmedumobile.domain.repository.schedule

import com.example.crmedumobile.domain.model.Lesson

interface ScheduleRepository {

    suspend fun getSchedule(): List<Lesson>
}