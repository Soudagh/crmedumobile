package com.example.crmedumobile.presentation.screen

import LocalDimensions
import android.graphics.Bitmap
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
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.example.crmedumobile.R
import com.example.crmedumobile.domain.model.Lesson
import com.example.crmedumobile.domain.model.enums.AttendanceStatusEnum
import com.example.crmedumobile.presentation.state.LessonUiState
import com.example.crmedumobile.presentation.theme.Black
import com.example.crmedumobile.presentation.theme.DarkPurple
import com.example.crmedumobile.presentation.theme.Gray
import com.example.crmedumobile.presentation.theme.RegularMontserrat16
import com.example.crmedumobile.presentation.theme.RegularMontserrat20
import com.example.crmedumobile.presentation.theme.RegularMontserrat24
import com.example.crmedumobile.presentation.theme.SemiBoldMontserrat24
import com.example.crmedumobile.presentation.theme.SemiBoldMontserrat32
import com.example.crmedumobile.presentation.theme.White
import com.example.crmedumobile.presentation.viewmodel.LessonViewModel
import com.google.zxing.BarcodeFormat
import com.google.zxing.qrcode.QRCodeWriter
import java.time.format.DateTimeFormatter
import androidx.core.graphics.createBitmap
import androidx.core.graphics.set
import com.example.crmedumobile.domain.model.LessonQr

@Composable
fun LessonScreen(
    navController: NavHostController,
    viewModel: LessonViewModel = hiltViewModel(),
    backStackEntry: NavBackStackEntry
) {
    val id = backStackEntry.arguments?.getLong("id") ?: 0
    val lessonState by viewModel.lessonState.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.loadLessonById(id)
    }

    when (val state = lessonState) {
        is LessonUiState.Success -> {
            LessonScreenContent(
                lesson = state.lesson,
                onEditAttendanceStatus = { attendanceId, status ->
                    viewModel.editAttendanceStatus(
                        attendanceId = attendanceId,
                        lessonId = id,
                        attendanceStatus = status
                    )
                },
                onEditLink = { viewModel.editLink(id, it) },
                onEditNotes = { viewModel.editNotes(id, it) },
                onCreateQrCode = { viewModel.createQrCode(it) },
                qrState = viewModel.qrCodeState.collectAsState().value
            )
        }

        is LessonUiState.Error -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Ошибка: ${state.message}")
            }
        }

        else -> {}
    }
}

@Composable
fun LessonScreenContent(
    lesson: Lesson,
    onEditAttendanceStatus: (Long, AttendanceStatusEnum) -> Unit,
    onEditLink: (String) -> Unit,
    onEditNotes: (String) -> Unit,
    onCreateQrCode: (Long) -> Unit,
    qrState: LessonQr?
) {

    val attendances = lesson.attendances
    var showLinkDialog by remember { mutableStateOf(false) }
    var tempLink by remember { mutableStateOf(lesson.link ?: "") }
    var showQrCode by remember { mutableStateOf(false) }
    var comments by remember { mutableStateOf(lesson.notes ?: "") }
    val attendanceOptions = AttendanceStatusEnum.entries.map(AttendanceStatusEnum::displayName)
    val formatter = DateTimeFormatter.ofPattern("HH:mm")

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
            horizontalAlignment = Alignment.Start
        ) {
            item {
                Spacer(modifier = Modifier.height(LocalDimensions.current.horizontalMedium))

                Text(
                    text = "Предмет: ${lesson.subject.name}",
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

                attendances.forEachIndexed { index, attendance ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        val student = attendance.student.user
                        Text(
                            text = "${student.surname} ${student.name} ${student.patronymic}",
                            style = RegularMontserrat16,
                            color = Color.Black,
                            modifier = Modifier.weight(1f)
                        )
                        var expanded by remember { mutableStateOf(false) }
                        Box {
                            Text(
                                text = attendance.attendanceStatus.displayName(),
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
                                            onEditAttendanceStatus(
                                                attendance.id,
                                                getEnumByDisplayName(option)
                                            )
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
                        text = "Ссылка на занятие: ${lesson.link ?: "Отсутствует"}",
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
                    text = "Время занятия: ${
                        lesson.startTime.toLocalTime().format(formatter)
                    }–${lesson.endTime.toLocalTime().format(formatter)}",
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
                        .clickable {
                            showQrCode = !showQrCode
                            if (showQrCode) onCreateQrCode(lesson.id)
                        }
                )
                if (showQrCode) {
                    qrState?.qrPayload?.let { qrPayload ->
                        val bitmap = remember(qrPayload) { generateQRCode(qrPayload, 512) }

                        Image(
                            bitmap = bitmap.asImageBitmap(),
                            contentDescription = "QR Code",
                            modifier = Modifier
                                .size(200.dp)
                                .align(Alignment.Start)
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "QR действует до: ${qrState.expiresAt}",
                            style = RegularMontserrat16,
                            color = Color.DarkGray
                        )
                    } ?: Text(
                        text = "Не удалось сгенерировать QR",
                        color = Color.Red,
                        style = RegularMontserrat16
                    )
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
                Spacer(modifier = Modifier.height(12.dp))

                TextButton(
                    modifier = Modifier.align(Alignment.End),
                    onClick = { onEditNotes(comments) }
                ) {
                    Text(
                        text = "Сохранить комментарий",
                        style = RegularMontserrat16,
                        color = DarkPurple
                    )
                }
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
                            onEditLink(tempLink)
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

fun AttendanceStatusEnum.displayName(): String = when (this) {
    AttendanceStatusEnum.PRESENCE -> "Присутствовал"
    AttendanceStatusEnum.NOT_PRESENCE -> "Отсутствовал"
    AttendanceStatusEnum.REASON -> "Уважительная причина"
}

fun getEnumByDisplayName(name: String): AttendanceStatusEnum =
    AttendanceStatusEnum.entries.find { it.displayName() == name }!!

fun generateQRCode(text: String, size: Int = 512): Bitmap {
    val writer = QRCodeWriter()
    val bitMatrix = writer.encode(text, BarcodeFormat.QR_CODE, size, size)
    val bmp = createBitmap(size, size, Bitmap.Config.RGB_565)

    for (x in 0 until size) {
        for (y in 0 until size) {
            bmp[x, y] = if (bitMatrix[x, y]) Color.Black.toArgb() else Color.White.toArgb()
        }
    }

    return bmp
}