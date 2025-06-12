package com.example.crmedumobile.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crmedumobile.domain.model.ScheduleModel
import com.example.crmedumobile.presentation.state.ScheduleState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScheduleStudentViewModel @Inject constructor(): ViewModel() {
    private val _state = MutableStateFlow<ScheduleState>(ScheduleState.Init)
    val state = _state.asStateFlow()
    init{
        getSchedule()
    }

    fun getSchedule(){
        val list = mutableListOf<ScheduleModel>()
        viewModelScope.launch(Dispatchers.IO) {
            //for loading screen
            _state.value = ScheduleState.Loading
            for (i in 1 until 20){
                list.add(ScheduleModel(
                    "13:00",
                    "Информатика",
                    "Групповое",
                    "ПОУ Программирование $i",
                    "Иванов Иван Иванович",
                    "https://www.youtube.com/watch?v=BmbM5B4NjxY"
                ))
            }
            _state.value = ScheduleState.Success(
                list,
                Pair("Среда","06.05.2025")
            )
        }
    }
}