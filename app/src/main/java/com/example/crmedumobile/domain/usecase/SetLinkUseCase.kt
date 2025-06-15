package com.example.crmedumobile.domain.usecase

import com.example.crmedumobile.domain.repository.lesson.LessonRepository

class SetLinkUseCase(private val lessonRepository: LessonRepository) {
    suspend operator fun invoke(id: Long, link: String) =
        lessonRepository.setLessonLinkById(id, link)
}