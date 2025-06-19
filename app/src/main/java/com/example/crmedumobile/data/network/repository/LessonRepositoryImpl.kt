package com.example.crmedumobile.data.network.repository

import com.example.crmedumobile.data.network.dtos.auth.PatchLessonLinkRequest
import com.example.crmedumobile.data.network.dtos.auth.PatchLessonNotesRequest
import com.example.crmedumobile.data.network.mapper.schedule.toDomain
import com.example.crmedumobile.data.network.service.LessonService
import com.example.crmedumobile.data.network.util.ErrorParser
import com.example.crmedumobile.domain.model.Lesson
import com.example.crmedumobile.domain.model.LessonQr
import com.example.crmedumobile.domain.repository.lesson.LessonRepository
import javax.inject.Inject

class LessonRepositoryImpl @Inject constructor(
    private val lessonService: LessonService,
    private val errorParser: ErrorParser
) : LessonRepository {
    override suspend fun getLessonById(id: Long): Lesson {
        return lessonService.getLessonById(id).toDomain()
    }

    override suspend fun setLessonLinkById(id: Long, link: String) {
        lessonService.setLessonLink(id, PatchLessonLinkRequest(link))
    }

    override suspend fun setLessonNotesById(id: Long, notes: String) {
        lessonService.setLessonNotes(id, PatchLessonNotesRequest(notes))
    }

    override suspend fun createLessonQr(id: Long): LessonQr {
        return lessonService.createLessonQr(id).toDomain()
    }
}