package com.example.crmedumobile.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crmedumobile.domain.model.ProfileData
import com.example.crmedumobile.domain.usecase.ChangeNotifyModeUseCase
import com.example.crmedumobile.domain.usecase.GetProfileUseCase
import com.example.crmedumobile.domain.usecase.LogOutUseCase
import com.example.crmedumobile.presentation.state.UserUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val profileUseCase: GetProfileUseCase,
    private val notifyModeUseCase: ChangeNotifyModeUseCase,
    private val logoutUseCase: LogOutUseCase
) : ViewModel() {

    private val _userState = MutableStateFlow<UserUiState>(UserUiState.Idle)
    val userState: StateFlow<UserUiState> = _userState

    fun profile() {
        viewModelScope.launch {
            _userState.value = UserUiState.Loading
            try {
                val user = profileUseCase()
                val profileData = ProfileData(
                    role = user.role.name,
                    fullName = "${user.surname} ${user.name} ${user.patronymic}",
                    notifications = user.notificationsEnabled
                )

                _userState.value = UserUiState.Success(profileData)
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
                val profileData = ProfileData(
                    role = updatedUser.role.name,
                    fullName = "${updatedUser.surname} ${updatedUser.name} ${updatedUser.patronymic}",
                    notifications = updatedUser.notificationsEnabled
                )
                _userState.value = UserUiState.Success(profileData)
            } catch (e: Exception) {
                _userState.value =
                    UserUiState.Error(e.message ?: "Ошибка изменения настроек уведомления")
            }
        }
    }

    fun logout() {
        logoutUseCase()
    }
}