package com.example.crmedumobile.domain.model

data class HomeworkAssignment(
    val id: Int,
    val title: String,
    val topic: String,
    val description: String,
    val fileName: String? = null,
    val isSubmitted: Boolean = false
)
