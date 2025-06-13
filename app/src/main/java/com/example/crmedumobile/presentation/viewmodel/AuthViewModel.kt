package com.example.crmedumobile.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crmedumobile.domain.model.Auth
import com.example.crmedumobile.domain.usecase.GetUserRoleUseCase
import com.example.crmedumobile.domain.usecase.LoginUseCase
import com.example.crmedumobile.presentation.state.LoginUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val roleUseCase: GetUserRoleUseCase
) : ViewModel() {

    private val _loginState = MutableStateFlow<LoginUiState>(LoginUiState.Idle)
    val loginState: StateFlow<LoginUiState> = _loginState

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _loginState.value = LoginUiState.Loading

            try {
                val jwt = loginUseCase(Auth(email, password))
                _loginState.value = LoginUiState.Success(jwt)
            } catch (e: Exception) {
                _loginState.value = LoginUiState.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun getRole(callback: (String) -> Unit) {
        viewModelScope.launch {
            val role = roleUseCase()
            callback(role)
        }
    }
}