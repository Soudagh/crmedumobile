package com.example.crmedumobile.data.network.util

import com.example.crmedumobile.data.network.dtos.auth.ErrorResponse
import com.example.crmedumobile.data.network.dtos.auth.ValidationErrorResponse
import com.squareup.moshi.Moshi
import retrofit2.HttpException
import javax.inject.Inject

class ErrorParser @Inject constructor(
    private val moshi: Moshi
) {
    fun parse(e: HttpException): String {
        val errorBody = e.response()?.errorBody()?.string()
        println(errorBody)
        val validation = moshi.adapter(ValidationErrorResponse::class.java).fromJson(errorBody ?: "")
        val generic = moshi.adapter(ErrorResponse::class.java).fromJson(errorBody ?: "")

        return when {
            validation?.violations?.isNotEmpty() == true ->
                validation.violations.joinToString("\n") { "${it.fieldName}: ${it.message}" }
            !generic?.error.isNullOrEmpty() -> "${generic?.error}"
            else -> "Неизвестная ошибка"
        }
    }
}
