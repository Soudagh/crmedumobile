package com.example.crmedumobile.domain.model

data class Program(
    val id: Long,
    val lessonCount: Int,
    val price: Double,
    val organization: Organization
)