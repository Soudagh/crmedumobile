package com.example.crmedumobile.presentation.screen

import LocalDimensions
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.crmedumobile.R
import com.example.crmedumobile.domain.model.ScheduleModel
import com.example.crmedumobile.presentation.components.ScheduleTutorItem
import com.example.crmedumobile.presentation.states.ScheduleUiState
import com.example.crmedumobile.presentation.theme.DarkPurple
import com.example.crmedumobile.presentation.theme.RegularMontserrat16
import com.example.crmedumobile.presentation.theme.RegularMontserrat24
import com.example.crmedumobile.presentation.theme.SemiBoldMontserrat32
import com.example.crmedumobile.presentation.viewmodel.ScheduleViewModel
import java.time.LocalDate

@Composable
fun ScheduleScreenTutor(
    navController: NavHostController,
    viewModel: ScheduleViewModel = hiltViewModel()
) {
    var lessons by remember { mutableStateOf(listOf<ScheduleModel>()) }
    var today by remember { mutableStateOf(Pair("", "")) }
    var selectedDay by remember { mutableIntStateOf(0) }

    val scheduleState by viewModel.scheduleState.collectAsState()

    val daysOfWeek = listOf(
        "Понедельник" to "05.05.2025",
        "Вторник" to "06.05.2025",
        "Среда" to "07.05.2025",
        "Четверг" to "08.05.2025",
        "Пятница" to "09.05.2025",
        "Суббота" to "10.05.2025",
        "Воскресенье" to "11.05.2025"
    )

    LaunchedEffect(Unit) {
        viewModel.loadSchedule(mode = "TUTOR")
    }

    LaunchedEffect(scheduleState) {
        when (val state = scheduleState) {
            is ScheduleUiState.Success -> {
                lessons = state.schedule
                val now = LocalDate.now()
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

    ScheduleScreenTutorContent(
        lessons = lessons,
        today = today,
        daysOfWeek = daysOfWeek,
        selectedDay = selectedDay,
        onDayChange = { selectedDay = it },
        lessonInfoClick = { navController.navigate("lesson/${it}") }
    )
}

@Composable
fun ScheduleScreenTutorContent(
    modifier: Modifier = Modifier,
    lessons: List<ScheduleModel>,
    today: Pair<String, String>,
    daysOfWeek: List<Pair<String, String>>,
    selectedDay: Int,
    onDayChange: (Int) -> Unit,
    lessonInfoClick: (Long) -> Unit,
) {
    Column(
        modifier = modifier
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
                text = "Расписание",
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

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(horizontal = LocalDimensions.current.horizontalMedium),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Spacer(modifier = Modifier.height(LocalDimensions.current.verticalMedium))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.arrow_back),
                        contentDescription = "Previous Day",
                        tint = DarkPurple,
                        modifier = Modifier
                            .size(32.dp)
                            .clickable {
                                if (selectedDay > 0) onDayChange(selectedDay - 1)
                            }
                    )

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = daysOfWeek[selectedDay].first,
                            style = RegularMontserrat24,
                            color = DarkPurple,
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = daysOfWeek[selectedDay].second,
                            style = RegularMontserrat16,
                            color = DarkPurple,
                            textAlign = TextAlign.Center
                        )
                    }

                    Icon(
                        painter = painterResource(id = R.drawable.arrow_forward),
                        contentDescription = "Next Day",
                        tint = DarkPurple,
                        modifier = Modifier
                            .size(32.dp)
                            .clickable {
                                if (selectedDay < daysOfWeek.size - 1) onDayChange(selectedDay + 1)
                            }
                    )
                }

                Spacer(modifier = Modifier.height(LocalDimensions.current.verticalMedium))
            }

            if (lessons.isEmpty()) {
                item {
                    Text(
                        text = "Нет занятий",
                        style = RegularMontserrat24,
                        color = Color.Black,
                        modifier = Modifier.padding(top = LocalDimensions.current.verticalMedium)
                    )
                }
            } else {
                items(lessons) { lesson ->
                    ScheduleTutorItem(
                        item = lesson,
                        lessonInfoClick = { lessonInfoClick(lesson.id!!) },
                        modifier = Modifier.fillMaxWidth(),
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ScheduleScreenTutorContentPreview() {
    val dummyLessons = listOf(
        ScheduleModel(
            id = 1,
            time = "09:00 – 10:30",
            name = "Физика",
            type = "Групповое",
            participant = "Группа С",
            teacher = "Кузнецов В.В.",
            color = "#FF8A65",
            date = "05.05.2025T09:00:00",
            attendanceStatus = "Присутствовал",
        ),
        ScheduleModel(
            id = 2,
            time = "11:00 – 12:30",
            name = "Информатика",
            type = "Индивидуальное",
            participant = "Сидорова М.М.",
            teacher = "Кузнецов В.В.",
            color = "#4DB6AC",
            date = "05.05.2025T11:00:00",
            attendanceStatus = "Отсутствовал",
        )
    )

    val daysOfWeek = listOf(
        "Понедельник" to "05.05.2025",
        "Вторник" to "06.05.2025",
        "Среда" to "07.05.2025",
        "Четверг" to "08.05.2025",
        "Пятница" to "09.05.2025",
        "Суббота" to "10.05.2025",
        "Воскресенье" to "11.05.2025"
    )

    ScheduleScreenTutorContent(
        lessons = dummyLessons,
        today = daysOfWeek[0],
        selectedDay = 0,
        onDayChange = {},
        daysOfWeek = daysOfWeek,
        lessonInfoClick = {}
    )
}
