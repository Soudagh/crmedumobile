package com.example.crmedumobile.presentation.states

import com.example.crmedumobile.domain.model.ScheduleModel

sealed class ScheduleUiState {
    object Idle : ScheduleUiState()
    object Loading : ScheduleUiState()
    data class Success(val schedule: List<ScheduleModel>) : ScheduleUiState()
    data class Error(val message: String) : ScheduleUiState()
}