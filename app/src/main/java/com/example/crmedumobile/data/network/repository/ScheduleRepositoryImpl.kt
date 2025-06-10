package com.example.crmedumobile.data.network.repository

import android.util.Log
import com.example.crmedumobile.data.network.mapper.schedule.toDomain
import com.example.crmedumobile.data.network.service.ScheduleService
import com.example.crmedumobile.data.network.util.ErrorParser
import com.example.crmedumobile.domain.model.Lesson
import com.example.crmedumobile.domain.repository.schedule.ScheduleRepository
import javax.inject.Inject

class ScheduleRepositoryImpl @Inject constructor(
    private val scheduleService: ScheduleService,
    private val errorParser: ErrorParser
) : ScheduleRepository {
    override suspend fun getSchedule(): List<Lesson>  {
        Log.d("ScheduleRepository", "Запрашиваем расписание...")
        return scheduleService.getUserSchedule().lessons
            .map { it.toDomain() }
    }
}