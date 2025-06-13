package com.example.crmedumobile.presentation.screen

import LocalDimensions
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.crmedumobile.R
import com.example.crmedumobile.presentation.theme.DarkPurple
import com.example.crmedumobile.presentation.theme.Gray
import com.example.crmedumobile.presentation.theme.RegularMontserrat16
import com.example.crmedumobile.presentation.theme.RegularMontserrat20
import com.example.crmedumobile.presentation.theme.RegularMontserrat24
import com.example.crmedumobile.presentation.theme.SemiBoldMontserrat24
import com.example.crmedumobile.presentation.theme.SemiBoldMontserrat32

data class Student(
    val name: String,
    var attendanceStatus: String
)

@Composable
fun LessonInfoScreenTutor(
    navController: NavHostController,
) {
    val students = remember {
        mutableStateListOf(
            Student("Осыкин Данил Юрьевич", "Отсутствовал"),
            Student("Иванова Анна Сергеевна", "subject.attendanceStatus"),
            Student("Петров Михаил Александрович", "subject.attendanceStatus")
        )
    }


//    LaunchedEffect(Unit) {
//        onEditClick(subject)
//    }

    var meetingLink by remember { mutableStateOf("https://meet.google.com/abc-123") }
    var showLinkDialog by remember { mutableStateOf(false) }
    var tempLink by remember { mutableStateOf(meetingLink) }
    var showQrCode by remember { mutableStateOf(false) }
    var comments by remember { mutableStateOf("") }
    val attendanceOptions = listOf("Присутствовал", "Отсутствовал", "Уваж. причина")

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
                text = "Информация",
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
            horizontalAlignment = Alignment.Start
        ) {
            item {
                Spacer(modifier = Modifier.height(LocalDimensions.current.horizontalMedium))

                Text(
                    text = "Предмет: ",
                    style = RegularMontserrat24,
                    color = Color.Black,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Ученики:",
                    style = RegularMontserrat24,
                    color = Color.Black,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))

                students.forEachIndexed { index, student ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = student.name,
                            style = RegularMontserrat16,
                            color = Color.Black,
                            modifier = Modifier.weight(1f)
                        )
                        var expanded by remember { mutableStateOf(false) }
                        Box {
                            Text(
                                text = student.attendanceStatus,
                                style = RegularMontserrat16,
                                color = Color.White,
                                modifier = Modifier
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(DarkPurple)
                                    .padding(horizontal = 12.dp, vertical = 8.dp)
                                    .clickable { expanded = true }
                            )
                            DropdownMenu(
                                expanded = expanded,
                                onDismissRequest = { expanded = false },
                                modifier = Modifier.background(Color.White)
                            ) {
                                attendanceOptions.forEach { option ->
                                    DropdownMenuItem(
                                        text = { Text(text = option, style = RegularMontserrat16) },
                                        onClick = {
                                            students[index] =
                                                student.copy(attendanceStatus = option)
                                            // Обновляем всех учеников на тот же статус
                                            students.forEachIndexed { i, s ->
                                                students[i] = s.copy(attendanceStatus = option)
                                            }
                                            // Передаём обновлённый предмет
//                                            onEditClick(
//                                                subject.copy(attendanceStatus = option)
//                                            )
                                            expanded = false
                                        }
                                    )
                                }
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Ссылка на занятие: $meetingLink",
                        style = RegularMontserrat16,
                        color = Color.Black,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 8.dp)
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.edit_icon),
                        contentDescription = "Edit Link",
                        tint = DarkPurple,
                        modifier = Modifier
                            .size(32.dp)
                            .clickable { showLinkDialog = true }
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Время занятия: 18:00–19:00",
                    style = RegularMontserrat20,
                    color = Color.Black,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Сгенерировать QR",
                    style = RegularMontserrat20,
                    color = DarkPurple,
                    textAlign = TextAlign.Left,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { showQrCode = !showQrCode }
                )
                if (showQrCode) {
                    Image(
                        painter = painterResource(id = R.drawable.qr),
                        contentDescription = "QR Code",
                        modifier = Modifier
                            .size(120.dp)
                            .align(Alignment.Start)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Комментарии:",
                    style = RegularMontserrat20,
                    color = Color.Black,
                    textAlign = TextAlign.Start
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = comments,
                    onValueChange = { comments = it },
                    placeholder = {
                        Text(
                            text = "Введите комментарий",
                            style = RegularMontserrat16,
                            color = Gray
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .border(
                            width = 1.dp,
                            color = DarkPurple,
                            shape = RoundedCornerShape(LocalDimensions.current.defaultCornerRadius)
                        ),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    textStyle = RegularMontserrat16
                )
            }
        }

        if (showLinkDialog) {
            AlertDialog(
                onDismissRequest = { showLinkDialog = false },
                title = { Text("Редактировать ссылку", style = SemiBoldMontserrat24) },
                text = {
                    TextField(
                        value = tempLink,
                        onValueChange = { tempLink = it },
                        label = { Text("Ссылка на занятие", style = RegularMontserrat16) },
                        modifier = Modifier.fillMaxWidth(),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White,
                            focusedIndicatorColor = DarkPurple,
                            unfocusedIndicatorColor = DarkPurple
                        )
                    )
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            meetingLink = tempLink
                            showLinkDialog = false
                        }
                    ) {
                        Text("Сохранить", style = RegularMontserrat16, color = DarkPurple)
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = { showLinkDialog = false }
                    ) {
                        Text("Отмена", style = RegularMontserrat16, color = DarkPurple)
                    }
                }
            )
        }
    }
}

