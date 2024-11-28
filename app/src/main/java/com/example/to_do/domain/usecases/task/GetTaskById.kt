package com.example.to_do.domain.usecases.task

import com.example.to_do.domain.repository.TaskRepository
import javax.inject.Inject

class GetTaskById @Inject constructor(
    private val taskRepository: TaskRepository
) {
    operator fun invoke(id : Long) = taskRepository.getTaskById(id)
}