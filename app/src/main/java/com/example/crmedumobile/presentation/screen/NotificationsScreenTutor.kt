package com.example.crmedumobile.presentation.screen

import LocalDimensions
import android.Manifest
import android.app.AlarmManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.example.crmedumobile.R
import com.example.crmedumobile.domain.model.ScheduleModel
import com.example.crmedumobile.presentation.states.forNotificationScheduler.NotificationPrefs
import com.example.crmedumobile.presentation.states.forNotificationScheduler.NotificationScheduler
import com.example.crmedumobile.presentation.theme.DarkPurple
import com.example.crmedumobile.presentation.theme.RegularMontserrat16
import com.example.crmedumobile.presentation.theme.RegularMontserrat20
import com.example.crmedumobile.presentation.theme.SemiBoldMontserrat32
import com.example.crmedumobile.presentation.theme.SubjectColors
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Duration.Companion.seconds

@Composable
fun NotificationsScreen(
    notificationPrefs: NotificationPrefs = NotificationPrefs(LocalContext.current),
    notificationScheduler: NotificationScheduler = NotificationScheduler(LocalContext.current)
) {
    val context = LocalContext.current

    var hasNotificationPermission by remember { mutableStateOf(false) }
    var canScheduleExactAlarms by remember { mutableStateOf(false) }
    var permissionRequested by remember { mutableStateOf(false) }

    val notificationPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        hasNotificationPermission = isGranted
    }
    var showPermissionWarning by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            canScheduleExactAlarms = alarmManager.canScheduleExactAlarms()

            if (!canScheduleExactAlarms) {
                val intent = Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM).apply {
                    data = android.net.Uri.parse("package:${context.packageName}")
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK
                }
                context.startActivity(intent)
            }
        } else {
            canScheduleExactAlarms = true
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val granted = ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED

            hasNotificationPermission = granted
            if (!granted && !permissionRequested) {
                permissionRequested = true
                notificationPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        } else {
            hasNotificationPermission = true
        }

        if (hasNotificationPermission && canScheduleExactAlarms) {
            val now = kotlinx.datetime.Clock.System.now()
            val triggerTime = now
                .plus(10.seconds)
                .toLocalDateTime(TimeZone.currentSystemDefault())

            notificationScheduler.scheduleNotification("test123", "Тестовое уведомление", triggerTime)
        }
    }

    val lessons = listOf(
        ScheduleModel(
            id = 1,
            time = "16:10",
            name = "Математика",
            type = "Индивидуальное",
            participant = "Иванов",
            color = SubjectColors[0].toString(),
            date = "2025-05-31T16:10:00Z"
        ),
        ScheduleModel(
            id = 2,
            time = "16:20",
            name = "Физика",
            type = "Групповое",
            participant = "СУ-303",
            color = SubjectColors[1].toString(),
            date = "2025-05-31T16:20:00Z"
        ),
        ScheduleModel(
            id = 3,
            time = "16:30",
            name = "Химия",
            type = "Индивидуальное",
            participant = "Петрова",
            color = SubjectColors[2].toString(),
            date = "2025-05-31T16:30:00Z"
        )
    )

    LaunchedEffect(hasNotificationPermission && canScheduleExactAlarms) {
        if (hasNotificationPermission && canScheduleExactAlarms) {
            lessons.forEach { lesson ->
                if (notificationPrefs.isNotificationEnabled(lesson.id.toString())) {
                    val dateTime = Instant.parse(lesson.date)
                        .toLocalDateTime(TimeZone.currentSystemDefault())
                    notificationScheduler.scheduleNotification(
                        lesson.id.toString(),
                        lesson.name,
                        dateTime
                    )
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
            item { Spacer(modifier = Modifier.height(LocalDimensions.current.verticalSmall)) }

            items(lessons) { lesson ->
                val lessonId = lesson.id.toString()
                var isNotificationEnabled by remember(lesson.id) {
                    mutableStateOf(notificationPrefs.isNotificationEnabled(lessonId))
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(lesson.name, style = RegularMontserrat20, color = Color.Black)
                        Text(
                            text = lesson.date.substringBefore("T")
                                .replace("-", ".") + " ${lesson.time}",
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
                                isNotificationEnabled = !isNotificationEnabled
                                notificationPrefs.setNotificationEnabled(
                                    lessonId,
                                    isNotificationEnabled
                                )

                                if (hasNotificationPermission && canScheduleExactAlarms) {
                                    val dateTime = Instant.parse(lesson.date)
                                        .toLocalDateTime(TimeZone.currentSystemDefault())
                                    if (isNotificationEnabled) {
                                        notificationScheduler.scheduleNotification(
                                            lessonId,
                                            lesson.name,
                                            dateTime
                                        )
                                    } else {
                                        notificationScheduler.cancelNotification(lessonId)
                                    }
                                } else {
                                    showPermissionWarning = true
                                }
                            }
                    )
                }

                HorizontalDivider(thickness = 0.5.dp, color = DarkPurple.copy(alpha = 0.2f))
            }
        }
    }
}
