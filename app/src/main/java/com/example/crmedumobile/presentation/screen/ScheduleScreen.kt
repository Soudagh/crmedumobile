package com.example.crmedumobile.presentation.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.crmedumobile.presentation.viewmodel.ScheduleViewModel

@Composable
fun ScheduleScreen(
    viewModel: ScheduleViewModel = hiltViewModel(),
) {
    val scheduleState by viewModel.scheduleState.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.loadSchedule()
    }

}

@Preview
@Composable
fun ScheduleScreenPreview() {
    ScheduleScreen()
}