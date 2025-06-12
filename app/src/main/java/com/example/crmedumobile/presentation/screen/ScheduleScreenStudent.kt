package com.example.crmedumobile.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.crmedumobile.R
import com.example.crmedumobile.domain.model.ScheduleModel
import com.example.crmedumobile.presentation.components.ScheduleStudentItem
import com.example.crmedumobile.presentation.states.ScheduleUiState
import com.example.crmedumobile.presentation.theme.BoldMontserrat36
import com.example.crmedumobile.presentation.theme.Purple40
import com.example.crmedumobile.presentation.viewmodel.ScheduleStudentViewModel
import java.time.LocalDate

@Composable
fun ScheduleScreenStudent(
    controller: NavHostController,
    viewModel: ScheduleStudentViewModel = hiltViewModel()
) {
    val scheduleState by viewModel.getSchedule()
    var list by remember { mutableStateOf(listOf<ScheduleModel>()) }
    var today by remember { mutableStateOf(Pair("", "")) }

    LaunchedEffect(Unit) {
        viewModel.loadSchedule()
    }

    LaunchedEffect(scheduleState) {
        when (val state = scheduleState) {
            is ScheduleUiState.Success -> {
                list = state.schedule
                val now = LocalDate.now()
                val todayLessons = list.filter { LocalDate.parse(it.date) == now }
                today = Pair(
                    now.dayOfWeek.name.lowercase().replaceFirstChar(Char::titlecase),
                    now.toString()
                )
            }

            is ScheduleUiState.Error -> {
                println("Ошибка: ${state.message}")
            }

            else -> {}
        }
    }

    ScheduleScreenStudentContent(list = list, today = today)
}

@Composable
fun ScheduleScreenStudentContent(
    modifier: Modifier = Modifier,
    list: List<ScheduleModel>,
    today: Pair<String, String>
) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        Text(
            stringResource(R.string.schedule),
            fontSize = 28.sp,
            color = com.example.crmedumobile.presentation.theme.Black,
            style = BoldMontserrat36,
            modifier = modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        HorizontalDivider()
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = {

            }, modifier = modifier.weight(1f)) {
                Icon(Icons.AutoMirrored.Default.KeyboardArrowLeft, null, tint = com.example.crmedumobile.presentation.theme.Black)
            }

            Column(
                modifier = modifier
                    .weight(11f)
                    .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(today.first, color = Purple40)
                Text(today.second, color = Purple40)
            }

            IconButton(onClick = {

            }, modifier = modifier.weight(1f)) {
                Icon(Icons.AutoMirrored.Default.KeyboardArrowRight, null, tint = com.example.crmedumobile.presentation.theme.Black)
            }
        }
        HorizontalDivider()
        LazyColumn(modifier = modifier.fillMaxSize()) {
            itemsIndexed(list) { _, item ->
                ScheduleStudentItem(item = item)
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun ScheduleScreenStudentPreview() {
    val dummyLessons = listOf(
        ScheduleModel(
            time = "10:00 – 11:30",
            science = "Math",
            group = "Group A",
            theme = "Algebra Basics",
            teacher = "Mr. Smith",
            link = "https://zoom.us/lesson/123"
        ),
        ScheduleModel(
            time = "12:00 – 13:30",
            science = "History",
            group = "Group B",
            theme = "WWII Overview",
            teacher = "Ms. Johnson",
            link = "https://zoom.us/lesson/456"
        )
    )

    ScheduleScreenStudentContent(
        list = dummyLessons,
        today = Pair("Monday", "2025-05-26")
    )
}