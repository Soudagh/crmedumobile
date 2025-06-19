package com.example.crmedumobile.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crmedumobile.domain.model.LessonQr
import com.example.crmedumobile.domain.model.enums.AttendanceStatusEnum
import com.example.crmedumobile.domain.usecase.CreateLessonQrUseCase
import com.example.crmedumobile.domain.usecase.GetLessonUseCase
import com.example.crmedumobile.domain.usecase.SetAttendanceUseCase
import com.example.crmedumobile.domain.usecase.SetLinkUseCase
import com.example.crmedumobile.domain.usecase.SetNotesUseCase
import com.example.crmedumobile.presentation.state.LessonUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LessonViewModel @Inject constructor(
    private val getLessonUseCase: GetLessonUseCase,
    private val setAttendanceUseCase: SetAttendanceUseCase,
    private val setLinkUseCase: SetLinkUseCase,
    private val setNotesUseCase: SetNotesUseCase,
    private val createLessonQrUseCase: CreateLessonQrUseCase
) : ViewModel() {

    private val _lessonState = MutableStateFlow<LessonUiState>(LessonUiState.Idle)
    val lessonState: StateFlow<LessonUiState> = _lessonState
    private val _qrCodeState = MutableStateFlow<LessonQr?>(null)
    val qrCodeState: StateFlow<LessonQr?> = _qrCodeState


    fun loadLessonById(id: Long) {
        viewModelScope.launch {
            _lessonState.value = LessonUiState.Loading
            try {
                val lesson = getLessonUseCase(id)
                _lessonState.value = LessonUiState.Success(lesson)
            } catch (e: Exception) {
                _lessonState.value = LessonUiState.Error(e.message ?: "Ошибка загрузки урока")
            }
        }
    }

    fun editAttendanceStatus(attendanceId: Long, lessonId: Long, attendanceStatus: AttendanceStatusEnum) {
        viewModelScope.launch {
            try {
                setAttendanceUseCase(id = attendanceId, attendanceStatus = attendanceStatus)
                val updatedLesson = getLessonUseCase(lessonId)
                _lessonState.value = LessonUiState.Success(updatedLesson)
            } catch (e: Exception) {
                _lessonState.value = LessonUiState.Error(e.message ?: "Ошибка изменения статуса посещения")
            }
        }
    }

    fun editLink(id: Long, link: String) {
        viewModelScope.launch {
            try {
                setLinkUseCase(id = id, link = link)
                val updatedLesson = getLessonUseCase(id)
                _lessonState.value = LessonUiState.Success(updatedLesson)
            } catch (e: Exception) {
                _lessonState.value = LessonUiState.Error(e.message ?: "Ошибка изменения ссылки")
            }
        }
    }

    fun editNotes(id: Long, notes: String) {
        viewModelScope.launch {
            try {
                setNotesUseCase(id = id, notes = notes)
                val updatedLesson = getLessonUseCase(id)
                _lessonState.value = LessonUiState.Success(updatedLesson)
            } catch (e: Exception) {
                _lessonState.value = LessonUiState.Error(e.message ?: "Ошибка изменения комментария")
            }
        }
    }

    fun createQrCode(id: Long) {
        viewModelScope.launch {
            try {
                val qr = createLessonQrUseCase(id)
                _qrCodeState.value = qr
            } catch (e: Exception) {
                _qrCodeState.value = null
            }

        }
    }
}