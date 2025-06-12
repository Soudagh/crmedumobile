package com.example.crmedumobile.presentation.state

import com.example.crmedumobile.domain.model.ScienceModel
import com.example.crmedumobile.domain.model.TheoryModel

sealed class TheoryUiState {
    data object Init : TheoryUiState()
    data object Loading : TheoryUiState()
    data class Success(val data: List<TheoryModel>, val scienceModel: ScienceModel) : TheoryUiState()
    data class Error(val message: String) : TheoryUiState()
}
