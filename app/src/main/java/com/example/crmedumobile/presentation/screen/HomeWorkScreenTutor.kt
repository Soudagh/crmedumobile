package com.example.crmedumobile.presentation.screen

import LocalDimensions
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.crmedumobile.presentation.components.BottomTabBar
import com.example.crmedumobile.presentation.states.Screen
import com.example.crmedumobile.presentation.theme.*

@Composable
fun HomeWorkScreen(
    onNavigate: (Screen) -> Unit,
    onCheckHomeworkClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .safeDrawingPadding()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = LocalDimensions.current.horizontalMedium),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Домашнее задание",
                style = SemiBoldMontserrat32,
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = LocalDimensions.current.verticalSmall)
            )
        }

        Divider(
            color = DarkPurple,
            thickness = 1.dp,
            modifier = Modifier.fillMaxWidth()
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(horizontal = LocalDimensions.current.horizontalMedium),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(LocalDimensions.current.verticalMedium))

            Button(
                onClick = onCheckHomeworkClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp),
                colors = ButtonDefaults.buttonColors(containerColor = SubjectColors[5]),
                border = BorderStroke(1.dp, DarkPurple)
            ) {
                Text(
                    text = "Проверить ДЗ",
                    style = BoldMontserrat24,
                    color = Color.Black
                )
            }
            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { /* TODO: Добавить функционал */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp),
                colors = ButtonDefaults.buttonColors(containerColor = SubjectColors[5]),
                border = BorderStroke(1.dp, DarkPurple)
            ) {
                Text(
                    text = "Составить ДЗ",
                    style = BoldMontserrat24,
                    color = Color.Black
                )
            }
            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { /* TODO: Добавить функционал */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp),
                colors = ButtonDefaults.buttonColors(containerColor = SubjectColors[5]),
                border = BorderStroke(1.dp, DarkPurple)
            ) {
                Text(
                    text = "Банк заданий",
                    style = BoldMontserrat24,
                    color = Color.Black
                )
            }
            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { /* TODO: Добавить функционал */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp),
                colors = ButtonDefaults.buttonColors(containerColor = SubjectColors[5]),
                border = BorderStroke(1.dp, DarkPurple)
            ) {
                Text(
                    text = "Готовые варианты",
                    style = BoldMontserrat24,
                    color = Color.Black
                )
            }

            Spacer(modifier = Modifier.weight(1f))
        }

        BottomTabBar(
            selectedScreen = Screen.NOTES,
            onScreenSelected = onNavigate
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeWorkScreenPreview() {
    CrmedumobileTheme {
        HomeWorkScreen(onNavigate = {}, onCheckHomeworkClick = {})
    }
}