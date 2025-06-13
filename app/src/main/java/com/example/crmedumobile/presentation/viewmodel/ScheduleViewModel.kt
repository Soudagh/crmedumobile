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
class ScheduleViewModel @Inject constructor(
    private val scheduleUseCase: GetScheduleUseCase
) : ViewModel() {

    private val _scheduleState = MutableStateFlow<ScheduleUiState>(ScheduleUiState.Idle)
    val scheduleState: StateFlow<ScheduleUiState> = _scheduleState

    fun loadSchedule(mode: String) {
        viewModelScope.launch {
            _scheduleState.value = ScheduleUiState.Loading
            try {
//                val lessons = scheduleUseCase()
//
//                val scheduleModels = when (mode) {
//                    "TUTOR" -> lessons.map { lesson ->
//                        ScheduleModel(
//                            id = lesson.id,
//                            time = "${lesson.startTime.toLocalTime()} - ${lesson.endTime.toLocalTime()}",
//                            name = lesson.subject.name,
//                            type = if (lesson.students?.size == 1) {
//                                "Груповое"
//                            } else "Индивидуальное",
//                            participant = if (lesson.students?.size == 1) {
//                                val student = lesson.students[0].user
//                                "${student.surname} ${student.name} ${student.patronymic}"
//                            } else lesson.program.name,
//                            color = lesson.color,
//                            date = lesson.date.toString()
//                        )
//                    }
//
//                    else -> lessons.map { lesson ->
//                        ScheduleModel(
//                            time = "${lesson.startTime.toLocalTime()} - ${lesson.endTime.toLocalTime()}",
//                            name = lesson.subject.name,
//                            participant = lesson.program.name,
//                            teacher = "${lesson.tutor.user.surname} ${lesson.tutor.user.name}",
//                            link = lesson.link,
//                            date = lesson.date.toString()
//                        )
//                    }
//                }

                val mockLessons = listOf(
                    ScheduleModel(
                        id = 1,
                        time = "09:00 – 10:30",
                        name = "Математика",
                        type = "Индивидуальное",
                        participant = if (mode == "TUTOR") "Иванов И.И." else "Группа A",
                        teacher = "Кузнецов В.В.",
                        color = "#FF8A65",
                        date = "05.05.2025",
                        attendanceStatus = "Присутствовал"
                    ),
                    ScheduleModel(
                        id = 2,
                        time = "11:00 – 12:30",
                        name = "История",
                        type = "Групповое",
                        participant = if (mode == "TUTOR") "Группа С" else "Группа B",
                        teacher = "Кузнецов В.В.",
                        color = "#4DB6AC",
                        date = "05.05.2025",
                        attendanceStatus = "Отсутствовал"
                    )
                )



//                _scheduleState.value = ScheduleUiState.Success(scheduleModels)
                _scheduleState.value = ScheduleUiState.Success(mockLessons)
            } catch (e: Exception) {
                _scheduleState.value =
                    ScheduleUiState.Error(e.message ?: "Ошибка загрузки расписания")
            }
        }
    }
}