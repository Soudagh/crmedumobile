package com.example.crmedumobile.domain.model

data class Student(
    val id: Long,
    val grade: Int,
    val balance: Double,
    val user: User
)