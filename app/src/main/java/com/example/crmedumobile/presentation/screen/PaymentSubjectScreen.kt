package com.example.crmedumobile.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import com.example.crmedumobile.R
import com.example.crmedumobile.presentation.theme.BoldMontserrat36
import com.example.crmedumobile.presentation.viewmodel.InformatikaPaymentViewModel

@Composable
fun PaymentSubjectScreen(
    modifier: Modifier = Modifier,
    backStackEntry: NavBackStackEntry
) {
    val viewModel: InformatikaPaymentViewModel = hiltViewModel()
    val subject = backStackEntry.arguments?.getString("subject") ?: ""
    val pkg by viewModel.paymentPackage.collectAsState()

    Column(modifier = Modifier.padding(16.dp)) {
        Text(subject, fontSize = 28.sp, color = Black, style = BoldMontserrat36, modifier = modifier.fillMaxWidth(), textAlign = TextAlign.Center)
        HorizontalDivider()
        Text(text = "Пакет занятий: ${pkg.group}")
        Text(text = "Преподаватель: ${pkg.teacher}")
        Spacer(modifier = modifier.height(24.dp))
        Button(onClick = { viewModel.pay() }) {
            Text("Оплатить")
        }
    }
}
