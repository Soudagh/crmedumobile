package com.example.crmedumobile.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.crmedumobile.domain.model.HomeworkAssignment
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FinishTaskViewModel @Inject constructor():ViewModel() {
    var assignment by mutableStateOf(
        HomeworkAssignment(
            id = 3,
            title = "Домашние задание",
            topic = "Пифагоровы тройки",
            description = "Загрузите листок с ответами в формате 1 – 1 (вариант вашего ответа). Уместите все записи на одной стороне бумаги."
        )
    )

    var fileName by mutableStateOf<String?>(null)
    var isSubmitted by mutableStateOf(false)

    fun attachFile(file: String) {
        fileName = file
    }

    fun submit() {
        isSubmitted = true
    }
}