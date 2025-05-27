package com.example.crmedumobile.presentation.state

import com.example.crmedumobile.domain.model.ScheduleModel

sealed class ScheduleState {
    data object Init: ScheduleState()
    data object Loading: ScheduleState()
    data class Success(val schedule: List<ScheduleModel>): ScheduleState()
    data class Error(val message: String): ScheduleState()
}