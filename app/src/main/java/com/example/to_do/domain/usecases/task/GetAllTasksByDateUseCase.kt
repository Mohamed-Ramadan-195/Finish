package com.example.to_do.domain.usecases.task

import com.example.to_do.domain.model.Task
import com.example.to_do.domain.repository.TaskRepository
import javax.inject.Inject

class GetAllTasksByDateUseCase @Inject constructor(
    private val taskRepository: TaskRepository
) {
    operator fun invoke(date: String) : List<Task> {
        return taskRepository.getAllTasksByDate(date)
    }
}