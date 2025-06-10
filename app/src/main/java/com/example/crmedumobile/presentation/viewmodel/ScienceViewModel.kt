package com.example.crmedumobile.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crmedumobile.domain.model.ScienceModel
import com.example.crmedumobile.domain.model.enums.StatusScience
import com.example.crmedumobile.presentation.state.ScienceState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScienceViewModel @Inject constructor(): ViewModel()
{
    private val elements = listOf("Математика", "Информатика", "Русский язык")
    private val _uiState = MutableStateFlow<ScienceState>(ScienceState.Init)
    val uiState = _uiState.asStateFlow()
    fun findElement(index: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.value = ScienceState.Loading
            delay(2000)
            _uiState.value = ScienceState.Success(listOf(
                ScienceModel(
                    "Пифагоровы тройки",
                    "18.05.2025",
                    StatusScience.Submitted
                ),
                ScienceModel(
                    "Пифагоровы тройки",
                    "18.05.2025",
                    StatusScience.NotSubmitted
                ),
                ScienceModel(
                    "Пифагоровы тройки",
                    "18.05.2025",
                    StatusScience.NotSubmitted
                ),
                ScienceModel(
                    "Пифагоровы тройки",
                    "18.05.2025",
                    StatusScience.NotSubmitted
                )
            ), elements[index])
        }
    }
}