package com.example.crmedumobile.data.network.service

import com.example.crmedumobile.data.network.dtos.auth.LessonDto
import com.example.crmedumobile.data.network.dtos.auth.LessonQrDto
import com.example.crmedumobile.data.network.dtos.auth.PatchLessonLinkRequest
import com.example.crmedumobile.data.network.dtos.auth.PatchLessonNotesRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface LessonService {

    @GET("lessons/{id}")
    suspend fun getLessonById(@Path("id") id: Long): LessonDto

    @POST("lessons/{id}/qr")
    suspend fun createLessonQr(@Path("id") id: Long): LessonQrDto

    @PATCH("lessons/{id}/notes")
    suspend fun setLessonNotes(@Path("id") id: Long, @Body request: PatchLessonNotesRequest)

    @PATCH("lessons/{id}/link")
    suspend fun setLessonLink(@Path("id") id: Long, @Body request: PatchLessonLinkRequest)
}