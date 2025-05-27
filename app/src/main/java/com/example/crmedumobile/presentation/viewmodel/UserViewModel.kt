package com.example.crmedumobile.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crmedumobile.domain.usecase.ProfileUseCase
import com.example.crmedumobile.presentation.states.UserUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val profileUseCase: ProfileUseCase,
) : ViewModel() {

    private val _userState = MutableStateFlow<UserUiState>(UserUiState.Idle)
    val userState: StateFlow<UserUiState> = _userState

    fun profile() {
        viewModelScope.launch {
            _userState.value = UserUiState.Loading
            try {
                val user = profileUseCase.invoke()
                _userState.value = UserUiState.Success(user)
            } catch (e: Exception) {
                _userState.value = UserUiState.Error(e.message ?: "Ошибка загрузки профиля")
            }
        }
    }
}