package com.example.crmedumobile.domain.model

import com.example.crmedumobile.domain.model.enum.TaskStatus
import java.time.LocalDateTime

data class CompletedTask(
    val id: Long,
    val task: Task,
    val studentAnswer: String,
    val status: TaskStatus,
    val dueDate: LocalDateTime
)
