package com.example.crmedumobile.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crmedumobile.domain.usecase.GetScheduleUseCase
import com.example.crmedumobile.presentation.states.ScheduleUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScheduleViewModel @Inject constructor(
    private val scheduleUseCase: GetScheduleUseCase
) : ViewModel() {

    private val _scheduleState = MutableStateFlow<ScheduleUiState>(ScheduleUiState.Idle)
    val scheduleState: StateFlow<ScheduleUiState> = _scheduleState

    fun loadSchedule() {
        viewModelScope.launch {
            _scheduleState.value = ScheduleUiState.Loading
            try {
                val schedule = scheduleUseCase()
                _scheduleState.value = ScheduleUiState.Success(schedule)
            } catch (e: Exception) {
                _scheduleState.value = ScheduleUiState.Error(e.message ?: "Ошибка загрузки расписания")
            }
        }
    }
}