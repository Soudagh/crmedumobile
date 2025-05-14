package com.example.crmedumobile.data.network.mapper.auth

import com.example.crmedumobile.data.network.dtos.auth.AuthRequest
import com.example.crmedumobile.data.network.dtos.auth.AuthResponse
import com.example.crmedumobile.domain.model.Auth
import com.example.crmedumobile.domain.model.Jwt

fun AuthRequest.toDomain(): Auth = Auth(email, password)

fun AuthResponse.toDomain(): Jwt = Jwt(type, accessToken, refreshToken)

fun Auth.toRequest() : AuthRequest = AuthRequest(email, password)