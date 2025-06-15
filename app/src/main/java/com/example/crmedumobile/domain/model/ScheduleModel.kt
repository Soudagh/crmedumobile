package com.example.crmedumobile.domain.model

data class ScheduleModel(
    val id: Long,
    val time: String,
    val name: String,
    val type: String? = null,
    val participant: String,
    val teacher: String? = null,
    val color: String,
    val date: String,
    val link: String? = null,
)