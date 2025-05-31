package com.example.crmedumobile.domain.model

import java.time.ZonedDateTime

data class CheckedTask(
    val id: Long,
    val completedTask: CompletedTask,
    val comments: String,
    val isChecked: Boolean,
    val inspectionDate: ZonedDateTime
)
