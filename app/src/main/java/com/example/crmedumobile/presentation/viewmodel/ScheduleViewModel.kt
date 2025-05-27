package com.example.crmedumobile.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crmedumobile.domain.model.ScheduleModel
import com.example.crmedumobile.presentation.state.ScheduleState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScheduleViewModel @Inject constructor(): ViewModel() {
    private val _state = MutableStateFlow<ScheduleState>(ScheduleState.Init)
    val state = _state.asStateFlow()

    fun getSchedule(){
        viewModelScope.launch(Dispatchers.IO) {
            //for loading screen
            _state.value = ScheduleState.Loading
            delay(2000)
            _state.value = ScheduleState.Success(
                listOf(
                    ScheduleModel(
                        "13:00",
                        "Информатика",
                        "Групповое",
                        "ПОУ Программирование",
                        "Иванов Иван Иванович",
                        "https://www.youtube.com/watch?v=BmbM5B4NjxY"
                    ),
                    ScheduleModel(
                        "13:00",
                        "Информатика",
                        "Групповое",
                        "ПОУ Программирование",
                        "Иванов Иван Иванович",
                        "https://www.youtube.com/watch?v=BmbM5B4NjxY"
                    ),
                    ScheduleModel(
                        "13:00",
                        "Информатика",
                        "Групповое",
                        "ПОУ Программирование",
                        "Иванов Иван Иванович",
                        "https://www.youtube.com/watch?v=BmbM5B4NjxY"
                    ),
                    ScheduleModel(
                        "13:00",
                        "Информатика",
                        "Групповое",
                        "ПОУ Программирование",
                        "Иванов Иван Иванович",
                        "https://www.youtube.com/watch?v=BmbM5B4NjxY"
                    )
                )
            )
        }
    }
}