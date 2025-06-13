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
import androidx.compose.material3.Divider
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

@Composable
fun NotificationsScreen(
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

    val notificationPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        hasNotificationPermission = isGranted
        showPermissionWarning = !isGranted
        println("POST_NOTIFICATIONS permission result: $isGranted")
    }

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
        ScheduleModel(
            time = "16:10",
            name = "Математика",
            type = "Индивидуальное",
            participant = "Иванов",
            color = SubjectColors[0].toString(),
            date = "2025-05-31T16:10:00Z"
        ),
        ScheduleModel(
            time = "16:20",
            name = "Физика",
            type = "Групповое",
            participant = "СУ-303",
            color = SubjectColors[1].toString(),
            date = "2025-05-31T16:20:00Z"
        ),
        ScheduleModel(
            time = "16:30",
            name = "Химия",
            type = "Индивидуальное",
            participant = "Петрова",
            color = SubjectColors[2].toString(),
            date = "2025-05-31T16:30:00Z"
        )
    )


    if (hasNotificationPermission && canScheduleExactAlarms) {
        LaunchedEffect(Unit) {
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
                    mutableStateOf(notificationPrefs.isNotificationEnabled(lesson.id.toString()))
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
                                println("Clicked notification icon for lesson id=${lesson.id}, current state=$isNotificationEnabled")
                                isNotificationEnabled = !isNotificationEnabled
                                notificationPrefs.setNotificationEnabled(
                                    lesson.id.toString(),
                                    isNotificationEnabled
                                )
                                println("New notification state for id=${lesson.id}: $isNotificationEnabled")
                                if (hasNotificationPermission && canScheduleExactAlarms) {
                                    if (isNotificationEnabled) {
                                        val dateTime = Instant.parse(lesson.date)
                                            .toLocalDateTime(TimeZone.currentSystemDefault())
                                        notificationScheduler.scheduleNotification(
                                            lesson.id.toString(),
                                            lesson.name,
                                            dateTime
                                        )
                                    } else {
                                        notificationScheduler.cancelNotification(lesson.id.toString())
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
    }
}

private fun openAppSettings(context: Context) {
    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
        data = Uri.fromParts("package", context.packageName, null)
    }
    context.startActivity(intent)
}

