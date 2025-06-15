package com.example.crmedumobile.presentation.state

import com.example.crmedumobile.domain.model.Lesson

sealed class LessonUiState {
    object Idle : LessonUiState()
    object Loading : LessonUiState()
    data class Success(val lesson: Lesson) : LessonUiState()
    data class Error(val message: String) : LessonUiState()
}