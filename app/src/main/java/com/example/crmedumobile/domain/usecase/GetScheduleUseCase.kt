package com.example.crmedumobile.domain.usecase

import com.example.crmedumobile.domain.model.Lesson
import com.example.crmedumobile.domain.repository.schedule.ScheduleRepository

class GetScheduleUseCase(private val scheduleRepository: ScheduleRepository) {
    suspend operator fun invoke(): List<Lesson> = scheduleRepository.getSchedule()
}
