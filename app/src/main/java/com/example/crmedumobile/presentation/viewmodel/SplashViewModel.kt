package com.example.crmedumobile.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crmedumobile.domain.model.enums.UserRole
import com.example.crmedumobile.domain.repository.auth.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _startDestination = MutableStateFlow<String?>(null)
    val startDestination: StateFlow<String?> = _startDestination

    init {
        viewModelScope.launch {
            val isLoggedIn = authRepository.isLoggedIn()
            val role = authRepository.getRole()

            _startDestination.value = when {
                !isLoggedIn -> "login"
                role == UserRole.TUTOR.name -> "tutor_nav"
                role == UserRole.STUDENT.name -> "student_nav"
                else -> "login"
            }
        }
    }
}
