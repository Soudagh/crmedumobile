package com.example.crmedumobile.data.network.repository

import android.content.SharedPreferences
import androidx.core.content.edit
import com.auth0.jwt.JWT
import com.auth0.jwt.interfaces.DecodedJWT
import com.example.crmedumobile.data.network.mapper.auth.toDomain
import com.example.crmedumobile.data.network.mapper.auth.toRequest
import com.example.crmedumobile.data.network.service.AuthService
import com.example.crmedumobile.data.network.util.ErrorParser
import com.example.crmedumobile.domain.model.Auth
import com.example.crmedumobile.domain.model.Jwt
import com.example.crmedumobile.domain.model.enums.UserRole
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
            sharedPreferences.edit {
                putString("jwt_token", jwt.accessToken)
                putString("user_role", decodeRoleFromJwt(jwt.accessToken))
            }
            jwt
        } catch (e: HttpException) {
            val errorMessage = errorParser.parse(e)
            throw AuthException(errorMessage)
        }
    }

    override fun isLoggedIn(): Boolean {
        val token = sharedPreferences.getString("jwt_token", null) ?: return false

        return try {
            val decodedJWT = JWT.decode(token)
            val expiresAt = decodedJWT.expiresAt
            expiresAt != null && expiresAt.after(java.util.Date())
        } catch (e: Exception) {
            logout()
            false
        }
    }

    override fun getRole(): String {
        return sharedPreferences.getString("user_role", null) ?: UserRole.STUDENT.name
    }

    override fun logout() {
        sharedPreferences.edit {
            remove("jwt_token")
            remove("user_role")
        }
    }


    private fun decodeRoleFromJwt(token: String): String? {
        return try {
            val decodedJWT: DecodedJWT = JWT.decode(token)
            decodedJWT.getClaim("role").asString()
        } catch (e: Exception) {
            null
        }
    }
}