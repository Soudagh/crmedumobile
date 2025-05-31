package com.example.crmedumobile.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crmedumobile.domain.model.Auth
<<<<<<< HEAD
import com.example.crmedumobile.presentation.states.LoginUiState
import com.example.crmedumobile.presentation.usecase.LoginUseCase
=======
import com.example.crmedumobile.domain.usecase.LoginUseCase
import com.example.crmedumobile.presentation.state.LoginUiState
>>>>>>> 7e266e1b99b341a8fad2a20e4a6e8ab033d91a41
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
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
}