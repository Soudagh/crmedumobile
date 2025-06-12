package com.example.crmedumobile.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.crmedumobile.domain.model.PaymentPackage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class InformatikaPaymentViewModel @Inject constructor() : ViewModel() {

    private val _package = MutableStateFlow(
        PaymentPackage(
            group = "Групповой 33",
            teacher = "Фролова К.А."
        )
    )
    val paymentPackage: StateFlow<PaymentPackage> = _package.asStateFlow()

    fun pay() {
        // payment API call or dummy success
        println("Payment success for Informatika")
    }
}
