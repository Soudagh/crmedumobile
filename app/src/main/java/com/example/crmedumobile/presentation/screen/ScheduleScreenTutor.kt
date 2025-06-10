package com.example.crmedumobile.presentation.screen

import LocalDimensions
import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.crmedumobile.R
import com.example.crmedumobile.presentation.components.BottomTabBar
import com.example.crmedumobile.presentation.components.ScheduleItem
import com.example.crmedumobile.presentation.states.Screen
import com.example.crmedumobile.presentation.states.forNotificationScheduler.ScheduleItemData
import com.example.crmedumobile.presentation.theme.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import androidx.compose.ui.platform.LocalContext

@Composable
fun ScheduleScreen(
    onNavigate: (Screen) -> Unit,
    onSubjectSelected: (ScheduleItemData) -> Unit
) {
    var selectedDay by remember { mutableStateOf(0) }
    // Состояние для хранения предметов
    val subjectsByDay = remember { mutableStateMapOf<Int, List<ScheduleItemData>>() }
    val daysOfWeek = listOf(
        "Понедельник" to "05.05.2025",
        "Вторник" to "06.05.2025",
        "Среда" to "07.05.2025",
        "Четверг" to "08.05.2025",
        "Пятница" to "09.05.2025",
        "Суббота" to "10.05.2025",
        "Воскресенье" to "11.05.2025"
    )

    // Получаем контекст для SharedPreferences
    val context = LocalContext.current
    // Загружаем сохранённые данные при старте
    LaunchedEffect(Unit) {
        loadSubjectsFromPreferences(context, subjectsByDay)
    }

    // Инициализация предметов
    val subjectsProvider: (Int) -> List<ScheduleItemData> = { dayIndex ->
        subjectsByDay.getOrPut(dayIndex) {
            when (dayIndex) {
                5, 6 -> emptyList()
                else -> listOf(
                    ScheduleItemData(
                        id = "${dayIndex}_1",
                        time = "10:00",
                        name = "Математика",
                        type = "Индивидуальное",
                        participant = "Иванов",
                        color = SubjectColors[0],
                        dateTime = "2025-05-0${dayIndex + 5}T10:00:00Z",
                        attendanceStatus = "Присутствовал"
                    ),
                    ScheduleItemData(
                        id = "${dayIndex}_2",
                        time = "11:30",
                        name = "Физика",
                        type = "Групповое",
                        participant = "СУ-303",
                        color = SubjectColors[1],
                        dateTime = "2025-05-0${dayIndex + 5}T11:30:00Z",
                        attendanceStatus = "Отсутствовал"
                    ),
                    ScheduleItemData(
                        id = "${dayIndex}_3",
                        time = "13:00",
                        name = "Химия",
                        type = "Индивидуальное",
                        participant = "Петрова",
                        color = SubjectColors[2],
                        dateTime = "2025-05-0${dayIndex + 5}T13:00:00Z",
                        attendanceStatus = "Уваж. причина"
                    ),
                    ScheduleItemData(
                        id = "${dayIndex}_4",
                        time = "14:30",
                        name = "Биология",
                        type = "Групповое",
                        participant = "МатПрофиль 2.1",
                        color = SubjectColors[3],
                        dateTime = "2025-05-0${dayIndex + 5}T14:30:00Z",
                        attendanceStatus = "Присутствовал"
                    ),
                    ScheduleItemData(
                        id = "${dayIndex}_5",
                        time = "16:00",
                        name = "Информатика",
                        type = "Индивидуальное",
                        participant = "Сидоров",
                        color = SubjectColors[4],
                        dateTime = "2025-05-0${dayIndex + 5}T16:00:00Z",
                        attendanceStatus = "Отсутствовал"
                    )
                )
            }
        }
    }

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
                text = "Расписание",
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
                                if (selectedDay > 0) selectedDay--
                            }
                    )

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
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
                                if (selectedDay < daysOfWeek.size - 1) selectedDay++
                            }
                    )
                }

                Spacer(modifier = Modifier.height(LocalDimensions.current.verticalMedium))
            }

            val subjects = subjectsProvider(selectedDay)
            if (subjects.isEmpty()) {
                item {
                    Text(
                        text = "Нет занятий",
                        style = RegularMontserrat24,
                        color = Color.Black,
                        modifier = Modifier.padding(top = LocalDimensions.current.verticalMedium)
                    )
                }
            } else {
                items(subjects) { subject ->
                    ScheduleItem(
                        item = subject,
                        modifier = Modifier.fillMaxWidth(),
                        onEditClick = { updatedSubject ->
                            // Обновляем предмет в состоянии
                            subjectsByDay[selectedDay] = subjectsByDay[selectedDay]!!.map {
                                if (it.id == updatedSubject.id) updatedSubject else it
                            }
                            // Сохраняем в SharedPreferences
                            saveSubjectsToPreferences(context, subjectsByDay)
                            onSubjectSelected(updatedSubject)
                        }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }

        BottomTabBar(
            selectedScreen = Screen.CALENDAR,
            onScreenSelected = onNavigate
        )
    }
}

// Вспомогательные функции для SharedPreferences
private fun saveSubjectsToPreferences(context: Context, subjectsByDay: Map<Int, List<ScheduleItemData>>) {
    val prefs = context.getSharedPreferences("SchedulePrefs", Context.MODE_PRIVATE)
    val json = Json.encodeToString(subjectsByDay)
    prefs.edit().putString("subjects", json).apply()
}

private fun loadSubjectsFromPreferences(context: Context, subjectsByDay: MutableMap<Int, List<ScheduleItemData>>) {
    val prefs = context.getSharedPreferences("SchedulePrefs", Context.MODE_PRIVATE)
    val json = prefs.getString("subjects", null)
    if (json != null) {
        try {
            val loadedSubjects = Json.decodeFromString<Map<Int, List<ScheduleItemData>>>(json)
            subjectsByDay.clear()
            subjectsByDay.putAll(loadedSubjects)
        } catch (e: Exception) {
            // Обработка ошибок десериализации
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ScheduleScreenPreview() {
    CrmedumobileTheme {
        ScheduleScreen(onNavigate = {}, onSubjectSelected = {})
    }
}