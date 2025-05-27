package com.example.crmedumobile.presentation.state

import com.example.crmedumobile.domain.model.Jwt

sealed class LoginUiState {
    object Idle : LoginUiState()
    object Loading : LoginUiState()
    data class Success(val jwt: Jwt) : LoginUiState()
    data class Error(val message: String) : LoginUiState()
}