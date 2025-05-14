package com.example.crmedumobile.domain.model

data class Tutor(
    val id: Long,
    val user: User,
    val socialContacts: List<String>,
    val notes: String
)
