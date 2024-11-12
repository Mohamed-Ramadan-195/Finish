package com.example.to_do.domain.usecases

import com.example.to_do.domain.model.Task
import com.example.to_do.domain.repository.TaskRepository
import javax.inject.Inject

class GetAllTasksUseCase @Inject constructor (
    private val tasksRepository: TaskRepository
) {
    operator fun invoke() : List<Task> = tasksRepository.getAllTasks()
}