package com.example.crmedumobile.presentation.state

import com.example.crmedumobile.domain.model.ProfileData

sealed class UserUiState {
    data object Idle : UserUiState()
    data object Loading : UserUiState()
    data class Success(val profile: ProfileData) : UserUiState()
    data class Error(val message: String) : UserUiState()
}
