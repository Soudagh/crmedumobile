package com.example.crmedumobile.presentation.state

import com.example.crmedumobile.domain.model.User

sealed class UserUiState {
    data object Idle : UserUiState()
    data object Loading : UserUiState()
    data class Success(val user: User) : UserUiState()
    data class Error(val message: String) : UserUiState()
}