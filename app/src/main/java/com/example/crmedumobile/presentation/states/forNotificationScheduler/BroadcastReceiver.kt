package com.example.crmedumobile.presentation.states.forNotificationScheduler

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.example.crmedumobile.R

class NotificationReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val lessonName = intent.getStringExtra("lesson_name") ?: "Урок"
        showNotification(context, lessonName)
    }

    private fun showNotification(context: Context, lessonName: String) {
        val channelId = "lesson_reminder_channel"
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Создание канала уведомлений только для API 26+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Lesson Reminders",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(channel)
        }

        // Создание уведомления
        val builder = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.ic_bell)
            .setContentTitle("Напоминание об уроке")
            .setContentText("Урок '$lessonName' начнётся через 5 минут!")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)

        // Для API < 26 канал игнорируется, поэтому можно использовать channelId
        notificationManager.notify(System.currentTimeMillis().toInt(), builder.build())
    }
}