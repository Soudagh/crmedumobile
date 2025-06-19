package com.example.crmedumobile.domain.repository.lesson

import com.example.crmedumobile.domain.model.Lesson
import com.example.crmedumobile.domain.model.LessonQr

interface LessonRepository {

    suspend fun getLessonById(id: Long): Lesson

    suspend fun setLessonLinkById(id: Long, link: String)

    suspend fun setLessonNotesById(id: Long, notes: String)

    suspend fun createLessonQr(id: Long): LessonQr
}