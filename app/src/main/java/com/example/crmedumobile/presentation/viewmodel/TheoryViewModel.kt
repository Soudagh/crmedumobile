package com.example.crmedumobile.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crmedumobile.R
import com.example.crmedumobile.domain.model.ScienceModel
import com.example.crmedumobile.domain.model.TheoryModel
import com.example.crmedumobile.domain.model.enums.StatusScience
import com.example.crmedumobile.presentation.state.TheoryUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TheoryViewModel @Inject constructor(): ViewModel(){
    private val list = listOf(
        ScienceModel(
            "Пифагоровы тройки",
            "18.05.2025",
            StatusScience.Submitted
        ),
        ScienceModel(
            "Пифагоровы тройки",
            "18.05.2025",
            StatusScience.NotSubmitted
        ),
        ScienceModel(
            "Пифагоровы тройки",
            "18.05.2025",
            StatusScience.NotSubmitted
        ),
        ScienceModel(
            "Пифагоровы тройки",
            "18.05.2025",
            StatusScience.NotSubmitted
        )
    )
    private val _uiState = MutableStateFlow<TheoryUiState>(TheoryUiState.Init)
    val uiState = _uiState.asStateFlow()
    fun getTheory(id:Int){
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.value = TheoryUiState.Loading
            _uiState.value = TheoryUiState.Success(
                listOf(
                    TheoryModel(
                        1,
                        R.drawable.question1,
                        1
                    ),
                    TheoryModel(
                        2,
                        R.drawable.question6,
                        2
                    ),
                    TheoryModel(
                        3,
                        R.drawable.question13,
                        1
                    )
                ),
                list[id]
            )
        }
    }
}
