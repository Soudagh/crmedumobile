package com.example.crmedumobile.domain.model

import com.example.crmedumobile.domain.model.enums.UserRole

<<<<<<< HEAD
=======

>>>>>>> 7e266e1b99b341a8fad2a20e4a6e8ab033d91a41
data class User(
    val id: Long,
    val surname: String,
    val name: String,
    val patronymic: String,
    val mail: String,
    val phone: String,
    val timezone: String,
    val role: UserRole,
<<<<<<< HEAD
    val organization: Organization,
=======
>>>>>>> 7e266e1b99b341a8fad2a20e4a6e8ab033d91a41
    val notificationsEnabled: Boolean
)