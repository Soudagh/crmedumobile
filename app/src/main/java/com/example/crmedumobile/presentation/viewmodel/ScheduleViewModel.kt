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
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class ScheduleViewModel @Inject constructor(
    private val scheduleUseCase: GetScheduleUseCase
) : ViewModel() {

    private val _scheduleState = MutableStateFlow<ScheduleUiState>(ScheduleUiState.Idle)
    val scheduleState: StateFlow<ScheduleUiState> = _scheduleState

    fun loadSchedule(mode: String) {
        viewModelScope.launch {
            _scheduleState.value = ScheduleUiState.Loading
            try {
                val lessons = scheduleUseCase()
                val formatter = DateTimeFormatter.ofPattern("HH:mm")

                val scheduleModels = when (mode) {
                    "TUTOR" -> lessons.map { lesson ->
                        ScheduleModel(
                            id = lesson.id,
                            time = lesson.startTime.toLocalTime().format(formatter),
                            name = lesson.subject.name,
                            type = if (lesson.attendances.size == 1) {
                                "Индивидуальное"
                            } else "Групповое",
                            participant = if (lesson.attendances.size == 1) {
                                val student = lesson.attendances[0].student.user
                                "${student.surname} ${student.name} ${student.patronymic}"
                            } else lesson.program.name,
                            color = lesson.color,
                            date = lesson.startTime.toString(),
                        )
                    }

                    else -> lessons.map { lesson ->
                        ScheduleModel(
                            id = lesson.id,
                            time = lesson.startTime.toLocalTime().format(formatter),
                            name = lesson.subject.name,
                            participant = lesson.program.name,
                            teacher = "${lesson.tutor.user.surname} ${lesson.tutor.user.name}",
                            link = lesson.link,
                            color = lesson.color,
                            date = lesson.startTime.toString()
                        )
                    }
                }

                _scheduleState.value = ScheduleUiState.Success(scheduleModels)
            } catch (e: Exception) {
                _scheduleState.value =
                    ScheduleUiState.Error(e.message ?: "Ошибка загрузки расписания")
            }
        }
    }
}