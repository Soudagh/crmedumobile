package com.example.crmedumobile.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crmedumobile.domain.model.ScheduleModel
import com.example.crmedumobile.domain.usecase.GetScheduleUseCase
import com.example.crmedumobile.presentation.states.ScheduleUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScheduleTutorViewModel @Inject constructor(
    private val scheduleUseCase: GetScheduleUseCase
) : ViewModel() {

    private val _scheduleState = MutableStateFlow<ScheduleUiState>(ScheduleUiState.Idle)
    val scheduleState: StateFlow<ScheduleUiState> = _scheduleState

    fun loadSchedule() {
        viewModelScope.launch {
            _scheduleState.value = ScheduleUiState.Loading
            try {
                val lessons = scheduleUseCase()

                val scheduleModels = lessons.map { lesson ->
                    ScheduleModel(
                        time = "${lesson.startTime.toLocalTime()} - ${lesson.endTime.toLocalTime()}",
                        science = lesson.subject.name,
                        group = lesson.program.name,
                        theme = lesson.notes,
                        teacher = "${lesson.tutor.user.surname} ${lesson.tutor.user.name}",
                        link = lesson.link,
                        date = lesson.date.toString()
                    )
                }

                _scheduleState.value = ScheduleUiState.Success(scheduleModels)
            } catch (e: Exception) {
                _scheduleState.value = ScheduleUiState.Error(e.message ?: "Ошибка загрузки расписания")
            }
        }
    }
}