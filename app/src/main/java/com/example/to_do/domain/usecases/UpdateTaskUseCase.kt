package com.example.to_do.domain.usecases

import com.example.to_do.domain.model.Task
import com.example.to_do.domain.repository.TaskRepository
import javax.inject.Inject

class UpdateTaskUseCase @Inject constructor(
    private val taskRepository: TaskRepository
) {

    suspend operator fun invoke(task: Task) {
        taskRepository.updateTask(task)
    }

}