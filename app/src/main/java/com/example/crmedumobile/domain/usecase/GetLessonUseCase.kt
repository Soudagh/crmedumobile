package com.example.crmedumobile.domain.usecase

import com.example.crmedumobile.domain.repository.lesson.LessonRepository

class GetLessonUseCase(private val lessonRepository: LessonRepository) {
    suspend operator fun invoke(id: Long) = lessonRepository.getLessonById(id)
}