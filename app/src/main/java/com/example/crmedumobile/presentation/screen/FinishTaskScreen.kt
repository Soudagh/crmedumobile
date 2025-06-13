package com.example.crmedumobile.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.crmedumobile.R
import com.example.crmedumobile.presentation.theme.BoldMontserrat36
import com.example.crmedumobile.presentation.theme.Purple40
import com.example.crmedumobile.presentation.viewmodel.FinishTaskViewModel

@Composable
fun FinishTaskScreen(controller: NavHostController) {
    val viewModel = hiltViewModel<FinishTaskViewModel>()
    val assignment = viewModel.assignment
    val fileName = viewModel.fileName
    val isSubmitted = viewModel.isSubmitted

    Column(
        modifier = Modifier
            .fillMaxSize().padding(16.dp)
    ) {
        Text(assignment.title, fontSize = 28.sp, color = Black, style = BoldMontserrat36, modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
        HorizontalDivider()
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = { controller.popBackStack() }) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
            }
            Spacer(Modifier.width(8.dp))
            Text(assignment.topic, fontSize = 23.sp, fontWeight = FontWeight.Medium, style = BoldMontserrat36)
        }
        HorizontalDivider()

        Spacer(Modifier.height(16.dp))
        Text("Файл Д/З:", fontWeight = FontWeight.Bold, color = Purple40)
        Spacer(Modifier.height(4.dp))
        Text(assignment.description, fontSize = 14.sp)

        Spacer(Modifier.height(16.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFF8D7DA), RoundedCornerShape(8.dp))
                .padding(16.dp)
        ) {
            Column {
                Text("📎 Прикрепить решение:")
                fileName?.let {
                    Spacer(Modifier.height(8.dp))
                    Text("📄 $it", color = Color.Green)
                }
            }
        }

        if (isSubmitted) {
            Spacer(Modifier.height(16.dp))
            controller.popBackStack("elements", inclusive = false)
            Text("✅ Домашнее задание отправлено!", color = Color.Green)
        }
        Button(
            onClick = { viewModel.submit() },
            colors = ButtonDefaults.buttonColors(Color(0xFFD2F4D1))
        ) {
            Text(stringResource(R.string.submit), color = Black)
        }
    }
}