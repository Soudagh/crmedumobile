package com.example.crmedumobile.presentation.state

import com.example.crmedumobile.domain.model.ScienceModel

sealed class ScienceState {
    data object Init:ScienceState()
    data object Loading:ScienceState()
    data class Success(val data: List<ScienceModel>, val science: String):ScienceState()
    data class Error(val message: String):ScienceState()
}