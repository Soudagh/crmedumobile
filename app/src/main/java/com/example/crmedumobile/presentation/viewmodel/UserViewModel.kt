package com.example.crmedumobile.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crmedumobile.domain.usecase.ChangeNotifyModeUseCase
import com.example.crmedumobile.domain.usecase.GetProfileUseCase
import com.example.crmedumobile.presentation.states.UserUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val profileUseCase: GetProfileUseCase,
    private val notifyModeUseCase: ChangeNotifyModeUseCase
) : ViewModel() {

    private val _userState = MutableStateFlow<UserUiState>(UserUiState.Idle)
    val userState: StateFlow<UserUiState> = _userState

    fun profile() {
        viewModelScope.launch {
            _userState.value = UserUiState.Loading
            try {
                val user = profileUseCase()
                _userState.value = UserUiState.Success(user)
            } catch (e: Exception) {
                _userState.value = UserUiState.Error(e.message ?: "Ошибка загрузки профиля")
            }
        }
    }

    fun changeNotifyMode(newMode: Boolean) {
        viewModelScope.launch {
            try {
                notifyModeUseCase(newMode)
                val updatedUser = profileUseCase()
                _userState.value = UserUiState.Success(updatedUser)
            } catch (e: Exception) {
                _userState.value =
                    UserUiState.Error(e.message ?: "Ошибка изменения настроек уведомления")
            }
        }
    }
}