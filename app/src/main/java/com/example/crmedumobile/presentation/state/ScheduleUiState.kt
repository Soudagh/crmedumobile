package com.example.crmedumobile.presentation.states

import com.example.crmedumobile.domain.model.Lesson

sealed class ScheduleUiState {
    object Idle : ScheduleUiState()
    object Loading : ScheduleUiState()
    data class Success(val schedule: List<Lesson>) : ScheduleUiState()
    data class Error(val message: String) : ScheduleUiState()
}