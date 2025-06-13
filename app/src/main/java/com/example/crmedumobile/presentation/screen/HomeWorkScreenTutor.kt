package com.example.crmedumobile.presentation.screen

import LocalDimensions
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.crmedumobile.presentation.theme.BoldMontserrat24
import com.example.crmedumobile.presentation.theme.CrmedumobileTheme
import com.example.crmedumobile.presentation.theme.DarkPurple
import com.example.crmedumobile.presentation.theme.SemiBoldMontserrat32
import com.example.crmedumobile.presentation.theme.SubjectColors

@Composable
fun HomeWorkScreenTutor(
    controller: NavHostController,
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

        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 1.dp,
            color = DarkPurple
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
                onClick = { controller.navigate("check_homework") },
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
    }
}

@Preview(showBackground = true)
@Composable
fun HomeWorkScreenTutorPreview() {
    CrmedumobileTheme {
//        HomeWorkScreen(onNavigate = {}, onCheckHomeworkClick = {})
    }
}