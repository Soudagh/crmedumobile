package com.example.crmedumobile.domain.usecase

import com.example.crmedumobile.domain.repository.lesson.LessonRepository

class SetNotesUseCase(private val lessonRepository: LessonRepository) {
    suspend operator fun invoke(id: Long, notes: String) = lessonRepository.setLessonNotesById(id, notes)
}