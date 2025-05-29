package com.example.crmedumobile.data.network.repository

import android.content.SharedPreferences
import androidx.core.content.edit
import com.example.crmedumobile.data.network.mapper.auth.toDomain
import com.example.crmedumobile.data.network.mapper.auth.toRequest
import com.example.crmedumobile.data.network.service.AuthService
import com.example.crmedumobile.data.network.util.ErrorParser
import com.example.crmedumobile.domain.model.Auth
import com.example.crmedumobile.domain.model.Jwt
import com.example.crmedumobile.domain.model.error.AuthException
import com.example.crmedumobile.domain.repository.auth.AuthRepository
import retrofit2.HttpException
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authService: AuthService,
    private val errorParser: ErrorParser,
    private val sharedPreferences: SharedPreferences,
) : AuthRepository {
    override suspend fun login(auth: Auth): Jwt {
        return try {
            val jwt = authService.login(auth.toRequest()).toDomain()
            sharedPreferences.edit() { putString("jwt_token", jwt.accessToken) }
            jwt
        } catch (e: HttpException) {
            val errorMessage = errorParser.parse(e)
            throw AuthException(errorMessage)
        }
    }
}