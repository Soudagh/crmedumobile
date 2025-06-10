package com.example.crmedumobile.presentation.state

sealed class ElementsUiState {
    object Init: ElementsUiState()
    object Loading: ElementsUiState()
    data class Success(val elements: List<String>): ElementsUiState()
    data class Error(val message: String): ElementsUiState()

}