package com.example.crmedumobile.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.crmedumobile.R
import com.example.crmedumobile.presentation.components.BottomTabBar
import com.example.crmedumobile.presentation.components.ScheduleItem
import com.example.crmedumobile.presentation.theme.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.example.crmedumobile.presentation.states.ScheduleItemData
import com.example.crmedumobile.presentation.states.Screen

@Composable
fun ScheduleScreen(
    onNavigate: (Screen) -> Unit
) {
    var selectedDay by remember { mutableStateOf(0) }
    val daysOfWeek = listOf(
        "Понедельник" to "05.05.2025",
        "Вторник" to "06.05.2025",
        "Среда" to "07.05.2025",
        "Четверг" to "08.05.2025",
        "Пятница" to "09.05.2025",
        "Суббота" to "10.05.2025",
        "Воскресенье" to "11.05.2025"
    )

    // Заглушка для получения данных из внешнего источника
    val subjectsProvider: (Int) -> List<ScheduleItemData> = { dayIndex ->
        when (dayIndex) {
            5, 6 -> emptyList()
            else -> listOf(
                ScheduleItemData("10:00", "Математика", "Индивидуальное", "Иванов", SubjectColors[0]),
                ScheduleItemData("11:30", "Физика", "Групповое", "СУ-303", SubjectColors[1]),
                ScheduleItemData("13:00", "Химия", "Индивидуальное", "Петрова", SubjectColors[2]),
                ScheduleItemData("14:30", "Биология", "Групповое", "МатПрофиль 2.1", SubjectColors[3]),
                ScheduleItemData("16:00", "Информатика", "Индивидуальное", "Сидоров", SubjectColors[4]),
                ScheduleItemData("16:00", "Информатика", "Индивидуальное", "Сидоров", SubjectColors[4])
            )
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .safeDrawingPadding()
            .padding(horizontal = paddingMedium)
    ) {
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Text(
                    text = "Расписание",
                    style = SemiBoldMontserrat32,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = paddingSmall)
                )
                Divider(
                    color = DarkPurple,
                    thickness = 1.dp,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(paddingMedium))

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

                Spacer(modifier = Modifier.height(paddingMedium))
            }

            val subjects = subjectsProvider(selectedDay)
            if (subjects.isEmpty()) {
                item {
                    Text(
                        text = "Нет занятий",
                        style = RegularMontserrat24,
                        color = Color.Black,
                        modifier = Modifier.padding(top = paddingMedium)
                    )
                }
            } else {
                items(subjects) { subject ->
                    ScheduleItem(
                        time = subject.time,
                        name = subject.name,
                        type = subject.type,
                        participant = subject.participant,
                        color = subject.color
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }

        BottomTabBar(
            selectedScreen = Screen.NOTES,
            onScreenSelected = onNavigate
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ScheduleScreenPreview() {
    CrmedumobileTheme {
        ScheduleScreen(onNavigate = {})
    }
}