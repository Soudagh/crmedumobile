package com.example.crmedumobile.domain.usecase

import com.example.crmedumobile.domain.repository.lesson.LessonRepository

class CreateLessonQrUseCase(private val lessonRepository: LessonRepository) {
    suspend operator fun invoke(id: Long) = lessonRepository.createLessonQr(id)
}