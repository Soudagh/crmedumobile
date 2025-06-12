package com.example.crmedumobile.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Index
import com.example.crmedumobile.presentation.state.ElementsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ElementsViewModel @Inject constructor(): ViewModel() {
    private val _uiState = MutableStateFlow<ElementsUiState>(ElementsUiState.Init)
    val uiState = _uiState.asStateFlow()
    init {
        getElements()
    }
    fun getElements() {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.value = ElementsUiState.Loading
            _uiState.value = ElementsUiState.Success(listOf("Математика", "Информатика", "Русский язык"))
        }
    }
}