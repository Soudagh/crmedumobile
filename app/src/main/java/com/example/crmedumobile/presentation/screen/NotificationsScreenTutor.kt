package com.example.crmedumobile.presentation.screen

import LocalDimensions
import android.Manifest
import android.app.AlarmManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.example.crmedumobile.R
import com.example.crmedumobile.presentation.components.BottomTabBar
import com.example.crmedumobile.presentation.states.Screen
import com.example.crmedumobile.presentation.states.forNotificationScheduler.NotificationPrefs
import com.example.crmedumobile.presentation.states.forNotificationScheduler.NotificationScheduler
import com.example.crmedumobile.presentation.states.forNotificationScheduler.ScheduleItemData
import com.example.crmedumobile.presentation.theme.*
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

@Composable
fun NotificationsScreen(
    onNavigate: (Screen) -> Unit,
    notificationPrefs: NotificationPrefs = NotificationPrefs(LocalContext.current),
    notificationScheduler: NotificationScheduler = NotificationScheduler(LocalContext.current)
) {
    val context = LocalContext.current
    var hasNotificationPermission by remember {
        mutableStateOf(
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.POST_NOTIFICATIONS
                ) == PackageManager.PERMISSION_GRANTED
            } else true
        )
    }
    var canScheduleExactAlarms by remember {
        mutableStateOf(
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
                alarmManager.canScheduleExactAlarms()
            } else true
        )
    }
    var showPermissionWarning by remember { mutableStateOf(false) }

    // Лаунчер для запроса POST_NOTIFICATIONS
    val notificationPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        hasNotificationPermission = isGranted
        showPermissionWarning = !isGranted
        println("POST_NOTIFICATIONS permission result: $isGranted")
    }

    // Запрос разрешений при запуске
    LaunchedEffect(Unit) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU && !hasNotificationPermission) {
            notificationPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S && !canScheduleExactAlarms) {
            showPermissionWarning = true
            println("Exact alarms not allowed: canScheduleExactAlarms=$canScheduleExactAlarms")
        }
    }

    val lessons = listOf(
        ScheduleItemData(
            id = "1",
            time = "16:10",
            name = "Математика",
            type = "Индивидуальное",
            participant = "Иванов",
            color = SubjectColors[0],
            dateTime = "2025-05-31T16:10:00Z" // ~20 минут от текущего времени
        ),
        ScheduleItemData(
            id = "2",
            time = "16:20",
            name = "Физика",
            type = "Групповое",
            participant = "СУ-303",
            color = SubjectColors[1],
            dateTime = "2025-05-31T16:20:00Z"
        ),
        ScheduleItemData(
            id = "3",
            time = "16:30",
            name = "Химия",
            type = "Индивидуальное",
            participant = "Петрова",
            color = SubjectColors[2],
            dateTime = "2025-05-31T16:30:00Z"
        )
    )


    if (hasNotificationPermission && canScheduleExactAlarms) {
        LaunchedEffect(Unit) {
            lessons.forEach { lesson ->
                if (notificationPrefs.isNotificationEnabled(lesson.id)) {
                    val dateTime = Instant.parse(lesson.dateTime)
                        .toLocalDateTime(TimeZone.currentSystemDefault())
                    notificationScheduler.scheduleNotification(lesson.id, lesson.name, dateTime)
                }
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
                text = "Уведомления",
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
            item { Spacer(modifier = Modifier.height(LocalDimensions.current.verticalSmall)) }
            items(lessons) { lesson ->
                var isNotificationEnabled by remember(lesson.id) {
                    mutableStateOf(notificationPrefs.isNotificationEnabled(lesson.id))
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = lesson.name,
                            style = RegularMontserrat20,
                            color = Color.Black
                        )
                        Text(
                            text = lesson.dateTime.substringBefore("T").replace("-", ".") + " ${lesson.time}",
                            style = RegularMontserrat16,
                            color = Color.Black
                        )
                    }
                    Icon(
                        painter = painterResource(
                            id = if (isNotificationEnabled) R.drawable.ic_bell else R.drawable.notifications_off
                        ),
                        contentDescription = "Toggle Notification",
                        tint = if (isNotificationEnabled) DarkPurple else Color.Red,
                        modifier = Modifier
                            .size(24.dp)
                            .clickable {
                                println("Clicked notification icon for lesson id=${lesson.id}, current state=$isNotificationEnabled")
                                isNotificationEnabled = !isNotificationEnabled
                                notificationPrefs.setNotificationEnabled(lesson.id, isNotificationEnabled)
                                println("New notification state for id=${lesson.id}: $isNotificationEnabled")
                                if (hasNotificationPermission && canScheduleExactAlarms) {
                                    if (isNotificationEnabled) {
                                        val dateTime = Instant.parse(lesson.dateTime)
                                            .toLocalDateTime(TimeZone.currentSystemDefault())
                                        notificationScheduler.scheduleNotification(lesson.id, lesson.name, dateTime)
                                    } else {
                                        notificationScheduler.cancelNotification(lesson.id)
                                    }
                                } else {
                                    showPermissionWarning = true
                                    println("Permissions missing: hasNotificationPermission=$hasNotificationPermission, canScheduleExactAlarms=$canScheduleExactAlarms")
                                }
                            }
                    )
                }
                Divider(color = DarkPurple.copy(alpha = 0.2f), thickness = 0.5.dp)
            }
        }


        BottomTabBar(
            selectedScreen = Screen.NOTIFICATIONS,
            onScreenSelected = onNavigate
        )
    }
}

private fun openAppSettings(context: Context) {
    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
        data = Uri.fromParts("package", context.packageName, null)
    }
    context.startActivity(intent)
}

