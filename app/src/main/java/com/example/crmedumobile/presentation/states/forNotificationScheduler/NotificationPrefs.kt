package com.example.crmedumobile.presentation.states.forNotificationScheduler

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

class NotificationPrefs(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("notification_prefs", Context.MODE_PRIVATE)

    fun isNotificationEnabled(id: String): Boolean {
        val isEnabled = prefs.getBoolean("notification_$id", true)
        println("NotificationPrefs: isNotificationEnabled for id=$id, enabled=$isEnabled")
        return isEnabled
    }

    fun setNotificationEnabled(id: String, enabled: Boolean) {
        println("NotificationPrefs: setNotificationEnabled for id=$id, enabled=$enabled")
        prefs.edit { putBoolean("notification_$id", enabled) }
    }
}

