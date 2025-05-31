package com.example.crmedumobile.domain.model

data class LessonMaterials(
    val id: Long,
    val lesson: Lesson,
    val name: String,
    val url: String
)
