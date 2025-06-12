package com.example.crmedumobile.presentation.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.crmedumobile.presentation.theme.BoldMontserrat36
import com.example.crmedumobile.presentation.viewmodel.PaymentViewModel

@Composable
fun PaymentScreen(
    modifier: Modifier = Modifier,
    onSubjectClick: (String) -> Unit,
) {
    val viewModel: PaymentViewModel = hiltViewModel()
    val subjects by viewModel.subjects.collectAsState()

    Column(modifier = modifier.padding(16.dp)) {
        Text("Оплата", fontSize = 28.sp, color = Black, style = BoldMontserrat36, modifier = modifier.fillMaxWidth(), textAlign = TextAlign.Center)
        HorizontalDivider()
        subjects.forEach { subject ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onSubjectClick(subject.subject) }
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = subject.subject)
                Text(
                    text = if (subject.isPaid) "Оплачено" else "Не оплачено",
                    color = if (subject.isPaid) Color.Green else Color.Red
                )
            }
        }
    }
}