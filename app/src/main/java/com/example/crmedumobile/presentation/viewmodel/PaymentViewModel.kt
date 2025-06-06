package com.example.crmedumobile.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.crmedumobile.domain.model.SubjectPaymentStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class PaymentViewModel @Inject constructor() : ViewModel() {

    private val _subjects = MutableStateFlow(
        listOf(
            SubjectPaymentStatus("Математика", true),
            SubjectPaymentStatus("Русский язык", true),
            SubjectPaymentStatus("Информатика", false)
        )
    )
    val subjects: StateFlow<List<SubjectPaymentStatus>> = _subjects.asStateFlow()

    fun markAsPaid(subjectName: String) {
        _subjects.update { list ->
            list.map {
                if (it.subject == subjectName) it.copy(isPaid = true) else it
            }
        }
    }
}
