package com.example.crmedumobile.domain.model

import com.example.crmedumobile.domain.model.enums.StatusScience

data class ScienceModel(
    val theme:String,
    val validate:String,
    val status: StatusScience
)